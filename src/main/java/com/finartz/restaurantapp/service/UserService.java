package com.finartz.restaurantapp.service;

import com.finartz.restaurantapp.model.dto.UserDto;
import com.finartz.restaurantapp.model.request.create.UserCreateRequest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface UserService extends UserDetailsService {

    @Override
    UserDetails loadUserByUsername(String email);

    UserDto getUser(Long id);

    UserDto getUser(String email);

    UserDto createUser(UserCreateRequest userCreateRequest);

    void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException;

}
