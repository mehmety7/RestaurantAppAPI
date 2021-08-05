package com.finartz.restaurantapp.service;

import com.finartz.restaurantapp.model.entity.UserEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {

    @Override
    UserDetails loadUserByUsername(String email);

    List<UserEntity> getUsers();

    UserEntity getUser(Long id);

    UserEntity getUser(String email);

    UserEntity createUser(UserEntity userEntity);

    UserEntity updateUser(UserEntity userEntity);

    UserEntity deleteUser(Long id);

}
