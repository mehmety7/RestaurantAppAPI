package com.finartz.restaurantapp.service;

import com.finartz.restaurantapp.model.dto.RestaurantDto;
import com.finartz.restaurantapp.model.enumerated.RestaurantStatus;
import com.finartz.restaurantapp.model.request.create.RestaurantCreateRequest;
import com.finartz.restaurantapp.model.request.update.RestaurantUpdateRequest;

import java.util.List;

public interface RestaurantService {

    List<RestaurantDto> getRestaurants(RestaurantStatus restaurantStatus);

    RestaurantDto getRestaurant(Long id);

    RestaurantDto createRestaurant(RestaurantCreateRequest restaurantCreateRequest);

    RestaurantDto updateRestaurantStatus(RestaurantUpdateRequest restaurantUpdateRequest);

    Boolean isRestaurantApproved(Long id);

}
