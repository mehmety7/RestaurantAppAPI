package com.finartz.restaurantapp.repository;

import com.finartz.restaurantapp.model.entity.AddressEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository extends BaseRepository<AddressEntity>{

    List<AddressEntity> getAddressEntitiesByUserEntity_Id(Long user_id);
    AddressEntity getAddressEntityByBranchEntity_Id(Long branch_id);

    @Query(value = "SELECT * FROM ADDRESSES a WHERE a.enable = true AND a.user_id = :user_id", nativeQuery = true)
    AddressEntity getActiveAddressByUserId(@Param("user_id") Long user_id);

}
