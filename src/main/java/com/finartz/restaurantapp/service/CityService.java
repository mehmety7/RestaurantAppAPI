package com.finartz.restaurantapp.service;

import com.finartz.restaurantapp.model.City;

import java.util.List;

public interface CityService {

    public City create(City city);

    public List<City> getAll();

    public City getById(Long id);

    public City update(City city);

    public City deleteById(Long id);
}
