package com.finartz.restaurantapp.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface TokenService {

    void checkRequestOwnerAuthoritative(Long entityOwnerId);

    String getUserEmailByToken(String token);

    void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException;

}
