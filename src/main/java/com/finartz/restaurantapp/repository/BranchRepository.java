package com.finartz.restaurantapp.repository;

import com.finartz.restaurantapp.model.entity.BranchEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BranchRepository extends BaseRepository<BranchEntity> {

    List<BranchEntity> getBranchEntitiesByAddressEntity_CountyEntity_Id(Long countyId);

    @Query(value = "SELECT r.user_id FROM branches b " +
            "LEFT JOIN restaurants r ON b.restaurant_id = :r.id",nativeQuery = true)
    Long getEntityOwnerUserIdByRestaurantId(@Param("r.id") Long restaurantId);

}
