package com.finartz.restaurantapp.repository;

import com.finartz.restaurantapp.model.entity.BranchEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BranchRepository extends BaseRepository<BranchEntity> {

    Page<BranchEntity> getBranchEntitiesByAddressEntity_CountyEntity_Id(Long countyId, Pageable paging);

    Integer countBranchEntitiesByAddressEntity_CountyEntity_Id(Long countyId);

    @Query(value = "SELECT r.user_id FROM branches b " +
            "LEFT JOIN restaurants r ON b.restaurant_id = :r.id",nativeQuery = true)
    Long getEntityOwnerUserIdByRestaurantId(@Param("r.id") Long restaurantId);

}
