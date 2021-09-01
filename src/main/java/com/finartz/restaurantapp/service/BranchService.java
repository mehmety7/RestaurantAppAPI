package com.finartz.restaurantapp.service;

import com.finartz.restaurantapp.model.dto.BranchDto;
import com.finartz.restaurantapp.model.dto.PageDto;
import com.finartz.restaurantapp.model.request.create.BranchCreateRequest;
import com.finartz.restaurantapp.model.request.get.BranchPageGetRequest;

public interface BranchService {

    BranchDto getBranch(Long id);

    PageDto<BranchDto> getBranches(BranchPageGetRequest branchPageGetRequest);

    BranchDto createBranch(BranchCreateRequest branchCreateRequest);

}
