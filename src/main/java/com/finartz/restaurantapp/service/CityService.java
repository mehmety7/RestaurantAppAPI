package com.finartz.restaurantapp.service;

import com.finartz.restaurantapp.model.entity.CityEntity;

import java.util.List;

public interface CityService {

    public List<CityEntity> getCities();

    public CityEntity getCity(Long id);

    public CityEntity createCity(CityEntity cityEntity);

    public CityEntity updateCity(CityEntity cityEntity);

    public CityEntity deleteCity(Long id);
}
