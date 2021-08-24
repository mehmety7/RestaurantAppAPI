package com.finartz.restaurantapp.service;

import com.finartz.restaurantapp.model.dto.MenuDto;
import com.finartz.restaurantapp.model.request.create.MenuCreateRequest;

public interface MenuService {

    MenuDto getMenu(Long id);

    MenuDto getMenuByBranchId(Long branchId);

    MenuDto getBranchMenu(Long branch_id);

    MenuDto createMenu(MenuCreateRequest menuCreateRequest);

}

