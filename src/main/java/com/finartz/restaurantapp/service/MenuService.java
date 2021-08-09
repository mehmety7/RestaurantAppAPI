package com.finartz.restaurantapp.service;

import com.finartz.restaurantapp.model.dto.MenuDto;
import com.finartz.restaurantapp.model.request.create.MenuCreateRequest;
import com.finartz.restaurantapp.model.request.update.MenuUpdateRequest;

public interface MenuService {

    MenuDto getMenu(Long id);

    MenuDto createMenu(MenuCreateRequest menuCreateRequest);

    MenuDto updateMenu(Long id, MenuUpdateRequest menuUpdateRequest);

}

