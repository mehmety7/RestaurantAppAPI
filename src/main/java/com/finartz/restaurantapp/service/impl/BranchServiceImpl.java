package com.finartz.restaurantapp.service.impl;

import com.finartz.restaurantapp.exception.EntityNotFoundException;
import com.finartz.restaurantapp.exception.InvalidStatusException;
import com.finartz.restaurantapp.model.converter.dtoconverter.BranchDtoConverter;
import com.finartz.restaurantapp.model.converter.entityconverter.fromCreateRequest.BranchCreateRequestToEntityConverter;
import com.finartz.restaurantapp.model.dto.BranchDto;
import com.finartz.restaurantapp.model.dto.RestaurantDto;
import com.finartz.restaurantapp.model.entity.BranchEntity;
import com.finartz.restaurantapp.model.enumerated.Status;
import com.finartz.restaurantapp.model.request.create.BranchCreateRequest;
import com.finartz.restaurantapp.repository.BranchRepository;
import com.finartz.restaurantapp.service.BranchService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Validator;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BranchServiceImpl implements BranchService {

    private final BranchRepository branchRepository;
    private final BranchDtoConverter branchDtoConverter;
    private final BranchCreateRequestToEntityConverter branchCreateRequestToEntityConverter;
    private final AddressServiceImpl addressService;
    private final RestaurantServiceImpl restaurantService;
    private final Validator validator;

    @Override
    public BranchDto getBranch(Long id) {
        return branchDtoConverter.convert(branchRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Not found Branch with id:" + id)
        ));
    }

    @Override
    public List<BranchDto> getBranches(Long countyId) {
        List<BranchDto> branches = new ArrayList<>();
        branchRepository.getBranchEntitiesByAddressEntity_CountyEntity_Id(countyId).forEach(branchEntity -> {
            branches.add(branchDtoConverter.convert(branchEntity));
        });
        return branches;
    }

    @Override
    @Transactional
    public BranchDto createBranch(BranchCreateRequest branchCreateRequest) {
        if(!isRestaurantApproved(branchCreateRequest.getRestaurantId()))
            throw new InvalidStatusException("The restaurant must be approved to create branch");
        BranchEntity branchEntity = branchRepository.save(branchCreateRequestToEntityConverter.convert(branchCreateRequest));
        branchCreateRequest.getAddressCreateRequest().setBranchId(branchEntity.getId());
        addressService.createAddress(branchCreateRequest.getAddressCreateRequest());
        return branchDtoConverter.convert(branchEntity);
    }

    private Boolean isRestaurantApproved(Long restaurantId){
        RestaurantDto restaurantDto = restaurantService.getRestaurant(restaurantId);
        if(restaurantDto.getStatus().toString().equals(Status.APPROVED))
            return true;
        else
            return false;
    }

}
