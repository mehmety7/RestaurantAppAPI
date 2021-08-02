package com.finartz.restaurantapp.service;

import com.finartz.restaurantapp.model.County;

import java.util.List;

public interface CountyService {

    public County create(County county);

    public List<County> getAll();

    public County getById(Long id);

    public County update(County county);

    public County deleteById(Long id);

}
