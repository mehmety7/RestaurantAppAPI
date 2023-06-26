package com.finartz.restaurantapp.controller;

import com.finartz.restaurantapp.model.dto.CityDto;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Objects;

@RunWith(SpringRunner.class)
@SpringBootTest(properties = "spring.main.banner-mode=off")
@ActiveProfiles("test")
public class CityControllerIntegrationTest {

    @Autowired
    private CityController cityController;

    @Test
    public void whenGetAllCity_thenReturnAllCity() {
        ResponseEntity<List<CityDto>> response = cityController.getCities();

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(34L, Objects.requireNonNull(response.getBody()).get(0).getId());
    }

    @Test
    public void whenGetCityById_thenReturnCity() {
        ResponseEntity<CityDto> response = cityController.getCity(34L);

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(34L, Objects.requireNonNull(response.getBody()).getId());
    }


    }
