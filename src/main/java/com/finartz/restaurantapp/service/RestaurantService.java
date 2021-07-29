package com.finartz.restaurantapp.service;

import com.finartz.restaurantapp.model.Restaurant;
import com.finartz.restaurantapp.model.enumerated.Status;
import com.finartz.restaurantapp.repository.RestaurantRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;

    public RestaurantService(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    public Restaurant create(Restaurant restaurant){
        return restaurantRepository.save(restaurant);
    }

    public List<Restaurant> getAll(){
        return restaurantRepository.findAll();
    }

    public Restaurant getById(Long id){
        return restaurantRepository.getById(id);
    }

    public List<Restaurant> findByStatus(Status status){
        return restaurantRepository.findByStatus(status);
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
