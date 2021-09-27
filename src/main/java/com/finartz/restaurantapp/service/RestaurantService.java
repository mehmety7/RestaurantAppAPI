package com.finartz.restaurantapp.service;

import com.finartz.restaurantapp.model.dto.RestaurantDto;
import com.finartz.restaurantapp.model.enumerated.RestaurantStatus;
import com.finartz.restaurantapp.model.request.create.RestaurantCreateRequest;
import com.finartz.restaurantapp.model.request.update.RestaurantUpdateRequest;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

@CacheConfig(cacheNames = {"restaurants"})
public interface RestaurantService {

    List<RestaurantDto> getRestaurants(RestaurantStatus restaurantStatus);

    @Cacheable
    RestaurantDto getRestaurant(Long id);

    RestaurantDto createRestaurant(RestaurantCreateRequest restaurantCreateRequest);

    @CacheEvict(value = "restaurants", key = "#restaurantUpdateRequest.getId()")
    RestaurantDto updateRestaurantStatus(RestaurantUpdateRequest restaurantUpdateRequest);

    Boolean isRestaurantApproved(Long id);

}
