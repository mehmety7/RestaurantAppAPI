package com.finartz.restaurantapp.repository;

import com.finartz.restaurantapp.model.dto.MenuDto;
import com.finartz.restaurantapp.model.entity.MenuEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuRepository extends BaseRepository<MenuEntity>{

    MenuDto getMenuEntityByBranchId(Long branch_id);

}
