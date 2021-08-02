package com.finartz.restaurantapp.service;

import com.finartz.restaurantapp.model.Menu;

import java.util.List;

public interface MenuService {

    public Menu create(Menu menu);

    public List<Menu> getAll();

    public Menu getById(Long id);

    public Menu update(Menu menu);

    public Menu deleteById(Long id);
}

