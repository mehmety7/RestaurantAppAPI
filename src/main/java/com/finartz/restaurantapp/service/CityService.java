package com.finartz.restaurantapp.service;

import com.finartz.restaurantapp.model.dto.CityDto;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

@CacheConfig(cacheNames = {"cities"})
public interface CityService {

    @Cacheable
    CityDto getCity(Long id);

    @Cacheable
    List<CityDto> getCities();

}
