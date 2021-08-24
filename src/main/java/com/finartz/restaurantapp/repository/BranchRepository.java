package com.finartz.restaurantapp.repository;

import com.finartz.restaurantapp.model.entity.BranchEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BranchRepository extends BaseRepository<BranchEntity> {

    List<BranchEntity> getBranchEntitiesByAddressEntity_CountyEntity_Id(Long countyId);

    List<BranchEntity> countBranchEntitiesByAddressEntity_CountyEntity_Id(Long countyId);

}
