package com.finartz.restaurantapp.service.impl;

import com.finartz.restaurantapp.model.entity.AddressEntity;
import com.finartz.restaurantapp.model.entity.BranchEntity;
import com.finartz.restaurantapp.model.enumerated.Status;
import com.finartz.restaurantapp.repository.BranchRepository;
import com.finartz.restaurantapp.service.BranchService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BranchServiceImpl implements BranchService {

    private final BranchRepository branchRepository;

    public BranchServiceImpl(BranchRepository branchRepository) {
        this.branchRepository = branchRepository;
    }

    @Override
    public List<BranchEntity> getBranches() {
        return branchRepository.findAll();
    }

    @Override
    public BranchEntity getBranch(Long id) {
        return branchRepository.getById(id);
    }

    @Override
    public List<BranchEntity> getBranches(Status status) {
        return branchRepository.findByStatus(status);
    }

    @Override
    public List<BranchEntity> getBranches(AddressEntity addressEntity) {
        return branchRepository.findByAddressEntity(addressEntity);
    }

    @Override
    public BranchEntity createBranch(BranchEntity branchEntity) {
        return branchRepository.save(branchEntity);
    }

    @Override
    public BranchEntity updateBranch(BranchEntity branchEntity) {
        BranchEntity foundBranchEntity = branchRepository.getById(branchEntity.getId());

        if (branchEntity.getName() != null)
            foundBranchEntity.setName(branchEntity.getName());
        if (branchEntity.getAddressEntity() != null)
            foundBranchEntity.setAddressEntity(branchEntity.getAddressEntity());
        if (branchEntity.getStatus() != null)
            foundBranchEntity.setStatus(branchEntity.getStatus());
        if (branchEntity.getMenuEntity() != null)
            foundBranchEntity.setMenuEntity(branchEntity.getMenuEntity());
        if (branchEntity.getRestaurantEntity() != null)
            foundBranchEntity.setRestaurantEntity(branchEntity.getRestaurantEntity());

        return branchRepository.save(foundBranchEntity);
    }

    @Override
    public BranchEntity deleteBranch(Long id) {
        BranchEntity branchEntity = branchRepository.getById(id);
        if (branchEntity != null) {
            branchRepository.deleteById(id);
            return branchEntity;
        }
        return branchEntity;
    }
}
