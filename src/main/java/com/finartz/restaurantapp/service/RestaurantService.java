package com.finartz.restaurantapp.service;

import com.finartz.restaurantapp.model.Restaurant;
import com.finartz.restaurantapp.model.enumerated.Status;

import java.util.List;

public interface RestaurantService {

    public Restaurant create(Restaurant restaurant);

    public List<Restaurant> getAll();

    public Restaurant getById(Long id);

    public List<Restaurant> findByStatus(Status status);

    public Restaurant update(Restaurant restaurant);

    public Restaurant deleteById(Long id);
}
