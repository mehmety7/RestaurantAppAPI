package com.finartz.restaurantapp.service;

import com.finartz.restaurantapp.model.entity.CountyEntity;

import java.util.List;

public interface CountyService {

    public List<CountyEntity> getCounties();

    public CountyEntity getCounty(Long id);

    public CountyEntity createCounty(CountyEntity countyEntity);

    public CountyEntity updateCounty(CountyEntity countyEntity);

    public CountyEntity deleteCounty(Long id);

}
