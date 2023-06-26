package com.finartz.restaurantapp.controller;

import com.finartz.restaurantapp.model.dto.UserDto;
import com.finartz.restaurantapp.model.enumerated.Role;
import com.finartz.restaurantapp.model.request.create.UserCreateRequest;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;
import java.util.Objects;

@RunWith(SpringRunner.class)
@SpringBootTest(properties = "spring.main.banner-mode=off")
@ActiveProfiles("test")
public class UserControllerIntegrationTest {

    @Autowired
    private UserController userController;

    @Test
    public void whenGetUserById_thenReturnUser() {
        ResponseEntity<UserDto> response = userController.getUser(1L);

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(1L, Objects.requireNonNull(response.getBody()).getId());
    }

    @Test
    public void whenCreateNewUser_thenReturnUser() {
        UserCreateRequest userCreateRequest = UserCreateRequest
                .builder()
                .name("name").email("email").password("pass").roles(Collections.singletonList(Role.USER)).addressCreateRequest(null)
                .build();

        ResponseEntity<UserDto> response = userController.createUser(userCreateRequest);

        Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());
        Assertions.assertEquals("name", Objects.requireNonNull(response.getBody()).getName());
    }



}
