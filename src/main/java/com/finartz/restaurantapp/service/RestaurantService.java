package com.finartz.restaurantapp.service;

import com.finartz.restaurantapp.model.dto.RestaurantDto;
import com.finartz.restaurantapp.model.enumerated.Status;
import com.finartz.restaurantapp.model.request.create.RestaurantCreateRequest;
import com.finartz.restaurantapp.model.request.update.RestaurantUpdateRequest;

import java.util.List;

public interface RestaurantService {

    List<RestaurantDto> getRestaurants(Status status);

    RestaurantDto getRestaurant(Long id);

    RestaurantDto createRestaurant(RestaurantCreateRequest restaurantCreateRequest);

    RestaurantDto updateRestaurant(RestaurantUpdateRequest restaurantUpdateRequest);

    Boolean isRestaurantApproved(Long id);

}
