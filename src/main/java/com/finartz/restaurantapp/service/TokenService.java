package com.finartz.restaurantapp.service;

import com.finartz.restaurantapp.model.dto.UserDto;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface TokenService {

    UserDto getUser(String token);

    void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException;

}
