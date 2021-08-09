package com.finartz.restaurantapp.service;

import com.finartz.restaurantapp.model.dto.BranchDto;
import com.finartz.restaurantapp.model.enumerated.Status;
import com.finartz.restaurantapp.model.request.create.BranchCreateRequest;
import com.finartz.restaurantapp.model.request.update.BranchUpdateRequest;

import java.util.List;

public interface BranchService {

    BranchDto getBranch(Long id);

    List<BranchDto> getBranches(Status status);

    List<BranchDto> getBranches(Long countyId);

    BranchDto createBranch(BranchCreateRequest branchCreateRequest);

    BranchDto updateBranch(Long id, BranchUpdateRequest branchUpdateRequest);

}
