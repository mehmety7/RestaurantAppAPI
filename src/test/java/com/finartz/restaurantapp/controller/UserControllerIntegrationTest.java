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
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(properties = "spring.main.banner-mode=off")
@TestPropertySource("classpath:application-test.properties")
public class UserControllerIntegrationTest {

    @Autowired
    private UserController userController;

    @Test
    public void whenGetUserById_thenReturnUser() {
        ResponseEntity<UserDto> response = userController.getUser(1l);

        Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);
        Assertions.assertEquals(response.getBody().getId(), 1l);
    }

    @Test
    public void whenCreateNewUser_thenReturnUser() {
        UserCreateRequest userCreateRequest = UserCreateRequest
                .builder()
                .name("name").email("email").password("pass").role(Role.USER).addressCreateRequest(null)
                .build();

        ResponseEntity<UserDto> response = userController.createUser(userCreateRequest);

        Assertions.assertEquals(response.getStatusCode(), HttpStatus.CREATED);
        Assertions.assertEquals(response.getBody().getName(), "name");
    }



}
