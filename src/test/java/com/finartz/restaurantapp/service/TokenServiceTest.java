package com.finartz.restaurantapp.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.finartz.restaurantapp.exception.EntityNotFoundException;
import com.finartz.restaurantapp.exception.InvalidOwnerException;
import com.finartz.restaurantapp.model.entity.UserEntity;
import com.finartz.restaurantapp.model.enumerated.Role;
import com.finartz.restaurantapp.repository.UserRepository;
import com.finartz.restaurantapp.service.impl.TokenServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpHeaders;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;

@RunWith(MockitoJUnitRunner.class)
public class TokenServiceTest {

    @InjectMocks
    private TokenServiceImpl tokenService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    private String token;
    private User user;

    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";
    private static final String ROLE = "USER";


    @Before
    public void init(){
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(ROLE));

        Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
        user = new User(USERNAME, PASSWORD, authorities);

        token = JWT.create()
                .withSubject(user.getUsername())
                .withClaim("roles", user.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                .sign(algorithm);

        token = "Bearer " + token;
    }

    @Test
    public void givenRequestOwnerIsEntityOwner_whenIsRequestOwnerAuthoritative_thenReturnNothing(){
        UserEntity requestOwner = UserEntity.builder().id(1L).email(USERNAME).roles(Collections.singletonList(Role.USER)).build();

        Mockito.when(request.getHeader("Authorization")).thenReturn(token);
        Mockito.when(userRepository.findByEmail(user.getUsername())).thenReturn(requestOwner);

        tokenService.checkRequestOwnerAuthoritative(1L);
        Mockito.verify(userRepository, Mockito.atLeastOnce()).findByEmail(anyString());
    }

    @Test(expected = InvalidOwnerException.class)
    public void givenRequestOwnerIsNotOwnerOfEntity_whenIsRequestOwnerAuthoritative_thenThrowInvalidOwnerException(){
        UserEntity requestOwner = UserEntity.builder().id(1L).email(USERNAME).roles(Collections.singletonList(Role.USER)).build();

        Mockito.when(request.getHeader("Authorization")).thenReturn(token);
        Mockito.when(userRepository.findByEmail(user.getUsername())).thenReturn(requestOwner);

        tokenService.checkRequestOwnerAuthoritative(20L);
    }

    @Test(expected = EntityNotFoundException.class)
    public void givenNullToken_whenIsRequestOwnerAuthoritative_thenThrowEntityNotFoundException(){

        Mockito.when(request.getHeader("Authorization")).thenReturn(null);
        tokenService.checkRequestOwnerAuthoritative(anyLong());

    }

    @Test
    public void givenValidToken_whenGetUserByToken_thenReturnUserEmail(){

        String result = tokenService.getUserEmailByToken(token);
        Assertions.assertEquals(result, user.getUsername());

    }

    @Test
    public void givenRequestIncludeValidToken_whenRefreshToken_thenSetResponseHeaderWithNewTokens() throws IOException {
        MockHttpServletRequest mockRequest = new MockHttpServletRequest("get", "/user/refresh-token");
        MockHttpServletResponse mockResponse = new MockHttpServletResponse();
        mockRequest.addHeader(HttpHeaders.AUTHORIZATION, token);

        UserEntity requestOwner = UserEntity.builder().id(1L).email(USERNAME).roles(Collections.singletonList(Role.USER)).build();
        Mockito.when(userRepository.findByEmail(anyString())).thenReturn(requestOwner);

        tokenService.refreshToken(mockRequest, mockResponse);

        Assertions.assertEquals("Bearer " + mockResponse.getHeader("refresh-token"), token);
        Assertions.assertNotNull(mockResponse.getHeader("access-token"));

    }

    @Test(expected = EntityNotFoundException.class)
    public void givenNullToken_whenRefreshToken_thenThrowEntityNotFoundException() throws IOException {

        Mockito.when(request.getHeader("Authorization")).thenReturn(null);
        tokenService.refreshToken(request, response);

    }

    @Test(expected = Exception.class)
    public void givenInvalidToken_whenRefreshToken_thenThrowException() throws IOException {

        Mockito.when(request.getHeader("Authorization")).thenReturn(token);
        Mockito.when(userRepository.findByEmail(user.getUsername())).thenReturn(null);
        tokenService.refreshToken(request, response);

    }
}
