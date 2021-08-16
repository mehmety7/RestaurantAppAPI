package com.finartz.restaurantapp.service;

import com.finartz.restaurantapp.model.dto.CountyDto;

import java.util.List;

public interface CountyService {

    CountyDto getCounty(Long id);

    List<CountyDto> getCounties(Long city_id);

}
