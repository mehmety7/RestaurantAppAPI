package com.finartz.restaurantapp.service;

import com.finartz.restaurantapp.model.dto.MenuDto;
import com.finartz.restaurantapp.model.request.create.MenuCreateRequest;

public interface MenuService {

    MenuDto getMenu(Long id);

    MenuDto getBranchMenu(Long branchId);

    MenuDto createMenu(MenuCreateRequest menuCreateRequest);

}

