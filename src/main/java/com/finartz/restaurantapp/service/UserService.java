package com.finartz.restaurantapp.service;

import com.finartz.restaurantapp.model.dto.UserDto;
import com.finartz.restaurantapp.model.request.create.UserCreateRequest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    @Override
    UserDetails loadUserByUsername(String email);

    UserDto getUser(Long id);

    UserDto getUser(String email);

    UserDto createUser(UserCreateRequest userCreateRequest);

}
