package com.finartz.restaurantapp.repository;

import com.finartz.restaurantapp.model.entity.CityEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CityRepository extends BaseRepository<CityEntity>{

    @Query("SELECT c FROM CityEntity c LEFT JOIN FETCH c.countyEntities")
    List<CityEntity> findAll();

}
