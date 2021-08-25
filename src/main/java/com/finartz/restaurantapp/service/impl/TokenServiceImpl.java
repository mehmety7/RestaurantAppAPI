package com.finartz.restaurantapp.service.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.finartz.restaurantapp.exception.EntityNotFoundException;
import com.finartz.restaurantapp.exception.InvalidOwnerException;
import com.finartz.restaurantapp.model.converter.dtoconverter.UserDtoConverter;
import com.finartz.restaurantapp.model.dto.UserDto;
import com.finartz.restaurantapp.model.entity.UserEntity;
import com.finartz.restaurantapp.model.enumerated.Role;
import com.finartz.restaurantapp.repository.UserRepository;
import com.finartz.restaurantapp.service.TokenService;
import lombok.RequiredArgsConstructor;
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
public class TokenServiceImpl implements TokenService {

    private final UserRepository userRepository;
    private final UserDtoConverter userDtoConverter;
    private final HttpServletRequest request;

    private final Integer  ACCESS_TOKEN_MINUTE = 10;

    @Override
    public UserDto getUser(String token) {
        if(token.startsWith("Bearer ")) {
            token = token.substring("Bearer ".length());
        }
        Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
        JWTVerifier verifier = JWT.require(algorithm).build();
        DecodedJWT decodedJWT = verifier.verify(token);
        String email = decodedJWT.getSubject();
        UserEntity userEntity = userRepository.findByEmail(email);
        if(Objects.nonNull(userEntity)) {
            return userDtoConverter.convert(userEntity);
        }else{
            throw new EntityNotFoundException("Not found User with email: " + email);
        }
    }

    @Override
    public Boolean isRequestOwnerAuthoritative(Long entityOwnerId) {
        String token = request.getHeader("Authorization");
        if(Objects.nonNull(token)){
            UserDto requestOwner = getUser(token);
            if (Objects.nonNull(token) && Objects.equals(entityOwnerId, requestOwner.getId())) {
                return true;
            }
            throw new InvalidOwnerException(requestOwner.getEmail());
        }else{
            throw new EntityNotFoundException("Access token may not be null");
        }
    }

    @Override
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        if(authorizationHeader != null && authorizationHeader.startsWith("Bearer ")){
            try {
                String refreshToken = authorizationHeader.substring("Bearer ".length());
                UserDto user = getUser(refreshToken);
                Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
                String accessToken = JWT.create()
                        .withSubject(user.getEmail())
                        .withExpiresAt(new Date(System.currentTimeMillis() + ACCESS_TOKEN_MINUTE * 60 * 1000))
                        .withIssuer(request.getRequestURL().toString())
                        .withClaim("roles", user.getRoles().stream().map(Role::name).collect(Collectors.toList()))
                        .sign(algorithm);

                response.setHeader("access-token", accessToken);
                response.setHeader("refresh-token", refreshToken);

            }catch(Exception exception){
                response.setHeader("error", exception.getMessage());
                response.setStatus(FORBIDDEN.value());
                Map<String,String> error = new HashMap<>();
                error.put("error_msg", exception.getMessage());
                response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(), error);
            }
        }else {
            throw new RuntimeException("Refresh token is missing");
        }
    }

}
