package com.finartz.restaurantapp.service;

import com.finartz.restaurantapp.model.entity.RestaurantEntity;
import com.finartz.restaurantapp.model.enumerated.Status;

import java.util.List;

public interface RestaurantService {

    List<RestaurantEntity> getRestaurants();

    List<RestaurantEntity> getRestaurants(Status status);

    RestaurantEntity getRestaurant(Long id);

    RestaurantEntity createRestaurant(RestaurantEntity restaurantEntity);

    RestaurantEntity updateRestaurant(RestaurantEntity restaurantEntity);

    RestaurantEntity deleteRestaurant(Long id);
}
