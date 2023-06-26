package com.finartz.restaurantapp.service.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.finartz.restaurantapp.exception.EntityNotFoundException;
import com.finartz.restaurantapp.exception.InvalidOwnerException;
import com.finartz.restaurantapp.model.constant.ConfigConstant;
import com.finartz.restaurantapp.model.entity.UserEntity;
import com.finartz.restaurantapp.model.enumerated.Role;
import com.finartz.restaurantapp.repository.UserRepository;
import com.finartz.restaurantapp.service.TokenService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.FORBIDDEN;

@Service
@RequiredArgsConstructor
@Slf4j
public class TokenServiceImpl implements TokenService {


    private final HttpServletRequest request;
    private final UserRepository userRepository;

    private static final Integer ACCESS_TOKEN_MINUTE = 60; // an hour

    @Override
    public void checkRequestOwnerAuthoritative(Long entityOwnerId) {
        String token = request.getHeader("Authorization");
        if(Objects.nonNull(entityOwnerId) && Objects.nonNull(token)){
            String requestOwnerMail = getUserEmailByToken(token);
            UserEntity requestOwner = userRepository.findByEmail(requestOwnerMail);
            if (Objects.equals(entityOwnerId, requestOwner.getId())) {
                return;
            }
            throw new InvalidOwnerException("Invalid owner: " + requestOwnerMail);
        } else {
            throw new EntityNotFoundException("Access token may not be null: " + entityOwnerId);
        }
    }

    @Override
    public String getUserEmailByToken(String token){
        if(token.startsWith(ConfigConstant.BEARER)) {
            token = token.substring(ConfigConstant.BEARER.length());
        }
        Algorithm algorithm = Algorithm.HMAC256(ConfigConstant.SECRET.getBytes());
        JWTVerifier verifier = JWT.require(algorithm).build();
        DecodedJWT decodedJWT = verifier.verify(token);
        return decodedJWT.getSubject();
    }

    @Override
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        if(authorizationHeader != null && authorizationHeader.startsWith(ConfigConstant.BEARER)){
            try {
                String refreshToken = authorizationHeader.substring(ConfigConstant.BEARER.length());
                String email = getUserEmailByToken(refreshToken);
                UserEntity user = userRepository.findByEmail(email);
                Algorithm algorithm = Algorithm.HMAC256(ConfigConstant.SECRET.getBytes());
                String accessToken = JWT.create()
                        .withSubject(user.getEmail())
                        .withExpiresAt(new Date(System.currentTimeMillis() + ACCESS_TOKEN_MINUTE * 60 * 1000))
                        .withIssuer(request.getRequestURL().toString())
                        .withClaim("roles", user.getRoles().stream().map(Role::name).collect(Collectors.toList()))
                        .sign(algorithm);
                response.setHeader("access-token", accessToken);
                response.setHeader("refresh-token", refreshToken);

            } catch(Exception exception){
                response.setHeader("error", exception.getMessage());
                response.setStatus(FORBIDDEN.value());
                Map<String,String> error = new HashMap<>();
                error.put("error_msg", exception.getMessage());
                response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(), error);
            }
        } else {
            throw new EntityNotFoundException("Refresh token is missing");
        }
    }

}
