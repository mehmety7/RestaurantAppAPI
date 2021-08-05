package com.finartz.restaurantapp.service.impl;

import com.finartz.restaurantapp.model.entity.RestaurantEntity;
import com.finartz.restaurantapp.model.enumerated.Status;
import com.finartz.restaurantapp.repository.RestaurantRepository;
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
    public List<RestaurantEntity> getRestaurants(){
        return restaurantRepository.findAll();
    }

    @Override
    public List<RestaurantEntity> getRestaurants(Status status){
        return restaurantRepository.findByStatus(status);
    }

    @Override
    public RestaurantEntity getRestaurant(Long id){
        return restaurantRepository.getById(id);
    }

    @Override
    public RestaurantEntity createRestaurant(RestaurantEntity restaurantEntity){
        return restaurantRepository.save(restaurantEntity);
    }

    @Override
    public RestaurantEntity updateRestaurant(RestaurantEntity restaurantEntity){
        RestaurantEntity foundRestaurantEntity = restaurantRepository.getById(restaurantEntity.getId());

        if (restaurantEntity.getName() != null)
            foundRestaurantEntity.setName(restaurantEntity.getName());
        if (restaurantEntity.getStatus() != null)
            foundRestaurantEntity.setStatus(restaurantEntity.getStatus());
        if (restaurantEntity.getUserEntity() != null)
            foundRestaurantEntity.setUserEntity(restaurantEntity.getUserEntity());
        if (restaurantEntity.getBranchEntities() != null)
            foundRestaurantEntity.setBranchEntities(restaurantEntity.getBranchEntities());

        return restaurantRepository.save(foundRestaurantEntity);

    }

    @Override
    public RestaurantEntity deleteRestaurant(Long id){
        RestaurantEntity restaurantEntity = restaurantRepository.getById(id);
        if (restaurantEntity != null) {
            restaurantRepository.deleteById(id);
            return restaurantEntity;
        }
        return restaurantEntity;
    }
}
