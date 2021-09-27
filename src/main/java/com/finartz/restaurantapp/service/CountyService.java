package com.finartz.restaurantapp.service;

import com.finartz.restaurantapp.model.dto.CountyDto;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

@CacheConfig(cacheNames = {"counties"})
public interface CountyService {

    @Cacheable
    CountyDto getCounty(Long id);

    @Cacheable
    List<CountyDto> getCounties(Long city_id);

}
