package com.finartz.restaurantapp.repository;

import com.finartz.restaurantapp.model.Restaurant;
import com.finartz.restaurantapp.model.enumerated.Status;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RestaurantRepository extends BaseRepository<Restaurant> {

    List<Restaurant> findByStatus(Status status);

}
