package com.finartz.restaurantapp.repository;

import com.finartz.restaurantapp.model.Restaurant;
import com.finartz.restaurantapp.model.enumerated.Status;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RestaurantRepository extends BaseRepository<Restaurant> {

    @Query(value = " SELECT * FROM RESTAURANTS r WHERE r.status = 'WAITING' ", nativeQuery = true)
    List<Restaurant> findByStatus(Status status);

}
