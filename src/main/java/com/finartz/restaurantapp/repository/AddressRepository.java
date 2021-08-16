package com.finartz.restaurantapp.repository;

import com.finartz.restaurantapp.model.entity.AddressEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository extends BaseRepository<AddressEntity>{

    List<AddressEntity> getAddressEntitiesByUserEntityId(Long user_id);
    List<AddressEntity> getAddressEntitiesByBranchEntityId(Long branch_id);

}
