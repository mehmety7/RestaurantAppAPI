package com.finartz.restaurantapp.repository;

import com.finartz.restaurantapp.model.entity.AddressEntity;
import com.finartz.restaurantapp.model.entity.BranchEntity;
import com.finartz.restaurantapp.model.enumerated.Status;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BranchRepository extends BaseRepository<BranchEntity> {

    List<BranchEntity> findByStatus(Status status);

    List<BranchEntity> findByAddressEntity(AddressEntity addressEntity);

}
