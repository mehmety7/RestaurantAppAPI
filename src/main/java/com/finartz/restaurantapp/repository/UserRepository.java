package com.finartz.restaurantapp.repository;

import com.finartz.restaurantapp.model.entity.UserEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends BaseRepository<UserEntity> {

    UserEntity findByEmail(String email);

}
