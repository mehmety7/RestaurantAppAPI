package com.finartz.restaurantapp.repository;

import com.finartz.restaurantapp.model.entity.AddressEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository extends BaseRepository<AddressEntity>{

    List<AddressEntity> getAddressEntitiesByUserEntityId(Long userId);

    AddressEntity getAddressEntityByBranchEntityId(Long branchId);

    @Query(value = "SELECT * FROM ADDRESSES a WHERE a.enable = true AND a.user_id = :userId", nativeQuery = true)
    AddressEntity getActiveAddressByUserId(@Param("userId") Long userId);

}
