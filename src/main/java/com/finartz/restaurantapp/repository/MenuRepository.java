package com.finartz.restaurantapp.repository;

import com.finartz.restaurantapp.model.entity.MenuEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuRepository extends BaseRepository<MenuEntity>{

    MenuEntity getMenuEntityByBranchEntity_Id(Long branch_id);

}
