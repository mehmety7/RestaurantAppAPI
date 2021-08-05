package com.finartz.restaurantapp.service;

import com.finartz.restaurantapp.model.entity.AddressEntity;
import com.finartz.restaurantapp.model.entity.BranchEntity;
import com.finartz.restaurantapp.model.enumerated.Status;

import java.util.List;

public interface BranchService {

    List<BranchEntity> getBranches();

    BranchEntity getBranch(Long id);

    List<BranchEntity> getBranches(Status status);

    List<BranchEntity> getBranches(AddressEntity addressEntity);

    BranchEntity createBranch(BranchEntity branchEntity);

    BranchEntity updateBranch(BranchEntity branchEntity);

    BranchEntity deleteBranch(Long id);
}
