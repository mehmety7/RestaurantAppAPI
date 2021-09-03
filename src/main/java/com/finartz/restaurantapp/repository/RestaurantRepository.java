package com.finartz.restaurantapp.repository;

import com.finartz.restaurantapp.model.entity.RestaurantEntity;
import com.finartz.restaurantapp.model.enumerated.RestaurantStatus;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RestaurantRepository extends BaseRepository<RestaurantEntity> {

    List<RestaurantEntity> findByRestaurantStatus(RestaurantStatus restaurantStatus);

}
