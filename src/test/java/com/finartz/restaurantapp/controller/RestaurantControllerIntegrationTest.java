package com.finartz.restaurantapp.controller;

import com.finartz.restaurantapp.model.dto.RestaurantDto;
import com.finartz.restaurantapp.model.enumerated.Status;
import com.finartz.restaurantapp.model.request.create.RestaurantCreateRequest;
import com.finartz.restaurantapp.model.request.update.RestaurantUpdateRequest;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(properties = "spring.main.banner-mode=off")
@TestPropertySource("classpath:application-test.properties")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RestaurantControllerIntegrationTest {

    @Autowired
    private RestaurantController restaurantController;

    @Test
    public void whenGetRestaurantById_thenReturnRestaurant() {
        ResponseEntity<RestaurantDto> response = restaurantController.getRestaurant(1l);

        Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);
        Assertions.assertEquals(response.getBody().getId(), 1l);
    }

    @Test
    public void whenCreateNewRestaurant_thenReturnRestaurant() {
        RestaurantCreateRequest restaurantCreateRequest = RestaurantCreateRequest
                .builder()
                .name("Restaurant")
                .status(Status.WAITING)
                .userId(2l)
                .build();

        ResponseEntity<RestaurantDto> response = restaurantController.createRestaurant(restaurantCreateRequest);

        Assertions.assertEquals(response.getStatusCode(), HttpStatus.CREATED);
        Assertions.assertEquals(response.getBody().getName(), "Restaurant");
        Assertions.assertEquals(response.getBody().getStatus(), Status.WAITING);

    }

    @Test
    public void whenGetRestaurantIsWaiting_thenReturnRestaurant() {
        ResponseEntity<List<RestaurantDto>> response = restaurantController.getRestaurants(Status.WAITING);

        Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);

        if(! response.getBody().isEmpty()) {
            Assertions.assertEquals(response.getBody().get(0).getStatus(), Status.WAITING);
        }
    }

    @Test
    public void whenUpdateRestaurant_thenReturnRestaurant() {
        RestaurantUpdateRequest restaurantUpdateRequest = RestaurantUpdateRequest
                .builder()
                .status(Status.CANCELED)
                .build();

        ResponseEntity<RestaurantDto> response = restaurantController.updateRestaurant(2l, restaurantUpdateRequest);

        Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);
        Assertions.assertNotEquals(response.getBody().getStatus(), Status.WAITING);
        Assertions.assertEquals(response.getBody().getStatus(), Status.CANCELED);
    }

}