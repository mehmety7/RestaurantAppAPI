package com.finartz.restaurantapp.service.impl;

import com.finartz.restaurantapp.exception.EntityNotFoundException;
import com.finartz.restaurantapp.exception.InvalidStatusException;
import com.finartz.restaurantapp.model.converter.dtoconverter.BranchDtoConverter;
import com.finartz.restaurantapp.model.converter.entityconverter.fromCreateRequest.BranchCreateRequestToEntityConverter;
import com.finartz.restaurantapp.model.dto.BranchDto;
import com.finartz.restaurantapp.model.entity.BranchEntity;
import com.finartz.restaurantapp.model.request.create.BranchCreateRequest;
import com.finartz.restaurantapp.model.request.create.MenuCreateRequest;
import com.finartz.restaurantapp.repository.BranchRepository;
import com.finartz.restaurantapp.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class BranchServiceImpl implements BranchService {

    private final BranchRepository branchRepository;
    private final BranchDtoConverter branchDtoConverter;
    private final BranchCreateRequestToEntityConverter branchCreateRequestToEntityConverter;

    private final RestaurantService restaurantService;
    private final AddressService addressService;
    private final MenuService menuService;

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
            if (!restaurantService.isRestaurantApproved(branchCreateRequest.getRestaurantId())) {
                throw new InvalidStatusException("The status of restaurant must be APPROVED by admin to create branch");
            }

            BranchEntity branchEntity = branchRepository.save(branchCreateRequestToEntityConverter.convert(branchCreateRequest));
            if (Objects.nonNull(branchCreateRequest.getAddressCreateRequest())) {
                branchCreateRequest.getAddressCreateRequest().setBranchId(branchEntity.getId());
                addressService.createAddress(branchCreateRequest.getAddressCreateRequest());
            }

            MenuCreateRequest menuCreateRequest = MenuCreateRequest.builder().branchId(branchEntity.getId()).build();
            menuService.createMenu(menuCreateRequest);

            return branchDtoConverter.convert(branchEntity);
    }
}
