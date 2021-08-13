package com.finartz.restaurantapp.repository;

import com.finartz.restaurantapp.model.entity.CountyEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface CountyRepository extends BaseRepository<CountyEntity> {

    public CountyEntity getCountyEntityByNameAndCityEntity_Id(String name, Long city_id);

}