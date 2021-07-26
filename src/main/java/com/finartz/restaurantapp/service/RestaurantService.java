package com.finartz.restaurantapp.service;

import com.finartz.restaurantapp.model.Restaurant;
import com.finartz.restaurantapp.model.enumerated.Status;
import com.finartz.restaurantapp.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestaurantService {

    @Autowired
    private RestaurantRepository restaurantRepository;

    public Restaurant create(Restaurant restaurant){
        Restaurant save = restaurantRepository.save(restaurant);
        return save;
    }

    public List<Restaurant> getAll(){
        List<Restaurant> restaurants = restaurantRepository.findAll();
        return restaurants;
    }

    public Restaurant getById(Long id){
        Restaurant restaurant = restaurantRepository.getById(id);
        return restaurant;
    }

    public List<Restaurant> getByStatus(Status status){
        List<Restaurant> restaurants = restaurantRepository.findByStatus(status);
        return restaurants;
    }

    public Restaurant update(Restaurant restaurant){
        Restaurant update = restaurantRepository.getById(restaurant.getId());
        if(update != null) {
            restaurantRepository.save(restaurant);
            return update;
        }
        return restaurant;
    }

    public Restaurant deleteById(Long id){
        Restaurant restaurant = restaurantRepository.getById(id);
        if (restaurant != null) {
            restaurantRepository.deleteById(id);
            return restaurant;
        }
        return restaurant;
    }
}
