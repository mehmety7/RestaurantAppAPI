package com.finartz.restaurantapp.controller;

import com.finartz.restaurantapp.model.dto.RestaurantDto;
import com.finartz.restaurantapp.model.enumerated.RestaurantStatus;
import com.finartz.restaurantapp.model.request.create.RestaurantCreateRequest;
import com.finartz.restaurantapp.model.request.update.RestaurantUpdateRequest;
import com.finartz.restaurantapp.service.TokenService;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.mockito.ArgumentMatchers.anyLong;

@RunWith(SpringRunner.class)
@SpringBootTest(properties = "spring.main.banner-mode=off")
@TestPropertySource("classpath:application-test.properties")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RestaurantControllerIntegrationTest {

    @Autowired
    private RestaurantController restaurantController;

    @MockBean
    private TokenService tokenService;

    @BeforeEach
    public void init(){
        Mockito.when(tokenService.isRequestOwnerAuthoritative(anyLong())).thenReturn(true);
    }

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
                .userId(2l)
                .build();

        ResponseEntity<RestaurantDto> response = restaurantController.createRestaurant(restaurantCreateRequest);

        Assertions.assertEquals(response.getStatusCode(), HttpStatus.CREATED);
        Assertions.assertEquals(response.getBody().getRestaurantStatus(), RestaurantStatus.WAITING);

    }

    @Test
    public void whenGetRestaurantIsWaiting_thenReturnRestaurant() {
        ResponseEntity<List<RestaurantDto>> response = restaurantController.getRestaurants(RestaurantStatus.WAITING);

        Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);

        if(! response.getBody().isEmpty()) {
            Assertions.assertEquals(response.getBody().get(0).getRestaurantStatus(), RestaurantStatus.WAITING);
        }
    }

    @Test
    public void whenUpdateRestaurant_thenReturnRestaurant() {
        RestaurantUpdateRequest restaurantUpdateRequest = RestaurantUpdateRequest
                .builder()
                .id(2l)
                .restaurantStatus(RestaurantStatus.CANCELED)
                .build();

        ResponseEntity<RestaurantDto> response = restaurantController.updateRestaurantStatus(restaurantUpdateRequest);

        Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);
        Assertions.assertNotEquals(response.getBody().getRestaurantStatus(), RestaurantStatus.WAITING);
        Assertions.assertEquals(response.getBody().getRestaurantStatus(), RestaurantStatus.CANCELED);
    }

}
