package com.finartz.restaurantapp.repository;

import com.finartz.restaurantapp.model.entity.CountyEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CountyRepository extends BaseRepository<CountyEntity> {

    List<CountyEntity> getCountyEntitiesByCityEntity_Id(Long city_id);

}
