package com.finartz.restaurantapp.service.impl;

import com.finartz.restaurantapp.model.Restaurant;
import com.finartz.restaurantapp.model.enumerated.Status;
import com.finartz.restaurantapp.repository.RestaurantRepository;
import com.finartz.restaurantapp.service.MenuService;
import com.finartz.restaurantapp.service.RestaurantService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestaurantServiceImpl implements RestaurantService {

    private final RestaurantRepository restaurantRepository;

    public RestaurantServiceImpl(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    @Override
    public Restaurant create(Restaurant restaurant){
        return restaurantRepository.save(restaurant);
    }

    @Override
    public List<Restaurant> getAll(){
        return restaurantRepository.findAll();
    }

    @Override
    public Restaurant getById(Long id){
        return restaurantRepository.getById(id);
    }

    @Override
    public List<Restaurant> findByStatus(Status status){
        return restaurantRepository.findByStatus(status);
    }

    @Override
    public Restaurant update(Restaurant restaurant){
        Restaurant foundRestaurant = restaurantRepository.getById(restaurant.getId());

        if (restaurant.getName() != null)
            foundRestaurant.setName(restaurant.getName());
        if (restaurant.getStatus() != null)
            foundRestaurant.setStatus(restaurant.getStatus());
        if (restaurant.getUser() != null)
            foundRestaurant.setUser(restaurant.getUser());
        if (restaurant.getBranchList() != null)
            foundRestaurant.setBranchList(restaurant.getBranchList());

        return restaurantRepository.save(foundRestaurant);

    }

    @Override
    public Restaurant deleteById(Long id){
        Restaurant restaurant = restaurantRepository.getById(id);
        if (restaurant != null) {
            restaurantRepository.deleteById(id);
            return restaurant;
        }
        return restaurant;
    }
}
