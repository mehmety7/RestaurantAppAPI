package com.finartz.restaurantapp.repository;

import com.finartz.restaurantapp.model.entity.MealEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MealRepository extends BaseRepository<MealEntity>{

    @Query(value = "SELECT r.user_id FROM meals m " +
            "LEFT JOIN menus mu ON :m.menu_id=mu.id " +
            "LEFT JOIN branches b ON mu.branch_id=b.id " +
            "LEFT JOIN restaurants r ON b.restaurant_id=r.id ", nativeQuery = true)
    Long getEntityOwnerUserIdByMenuId(@Param("m.menu_id") Long menuId);

}
