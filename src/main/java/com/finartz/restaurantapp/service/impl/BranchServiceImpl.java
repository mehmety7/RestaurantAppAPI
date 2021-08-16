package com.finartz.restaurantapp.service.impl;

import com.finartz.restaurantapp.exception.EntityNotFoundException;
import com.finartz.restaurantapp.model.converter.dtoconverter.BranchDtoConverter;
import com.finartz.restaurantapp.model.converter.entityconverter.fromCreateRequest.BranchCreateRequestToEntityConverter;
import com.finartz.restaurantapp.model.dto.BranchDto;
import com.finartz.restaurantapp.model.entity.BranchEntity;
import com.finartz.restaurantapp.model.request.create.BranchCreateRequest;
import com.finartz.restaurantapp.repository.BranchRepository;
import com.finartz.restaurantapp.service.BranchService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BranchServiceImpl implements BranchService {

    private final BranchRepository branchRepository;

    private final BranchDtoConverter branchDtoConverter;
    private final BranchCreateRequestToEntityConverter branchCreateRequestToEntityConverter;

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
    public BranchDto createBranch(BranchCreateRequest branchCreateRequest) {
        BranchEntity branchEntity = branchCreateRequestToEntityConverter.convert(branchCreateRequest);
        return branchDtoConverter.convert(branchRepository.save(branchEntity));
    }

}
