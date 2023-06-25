package com.finartz.restaurantapp.service;

import com.finartz.restaurantapp.model.dto.CityDto;

import java.util.List;

public interface CityService {

    CityDto getCity(Long id);

    List<CityDto> getCities();
}
