package com.finartz.restaurantapp.service;

import com.finartz.restaurantapp.model.entity.MenuEntity;

import java.util.List;

public interface MenuService {

    List<MenuEntity> getMenus();

    MenuEntity getMenu(Long id);

    MenuEntity createMenu(MenuEntity menuEntity);

    MenuEntity updateMenu(MenuEntity menuEntity);

    MenuEntity deleteMenu(Long id);

}

