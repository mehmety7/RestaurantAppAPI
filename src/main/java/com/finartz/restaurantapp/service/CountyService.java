package com.finartz.restaurantapp.service;

import com.finartz.restaurantapp.model.dto.CountyDto;

public interface CountyService {

    CountyDto getCounty(Long id);

    CountyDto getCounty(String name, Long city_id);

}
