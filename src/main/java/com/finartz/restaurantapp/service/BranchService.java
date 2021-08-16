package com.finartz.restaurantapp.service;

import com.finartz.restaurantapp.model.dto.BranchDto;
import com.finartz.restaurantapp.model.request.create.BranchCreateRequest;

import java.util.List;

public interface BranchService {

    BranchDto getBranch(Long id);

    List<BranchDto> getBranches(Long countyId);

    BranchDto createBranch(BranchCreateRequest branchCreateRequest);

}
