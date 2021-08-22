package com.finartz.restaurantapp.controller;

import com.finartz.restaurantapp.model.dto.CityDto;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
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
public class CityControllerIntegrationTest {

    @Autowired
    private CityController cityController;

    @Test
    public void whenGetAllCity_thenReturnAllCity() {
        ResponseEntity<List<CityDto>> response = cityController.getCities();

        Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);
        Assertions.assertEquals(response.getBody().get(0).getId(), 34l);
    }

    @Test
    public void whenGetCityById_thenReturnCity() {
        ResponseEntity<CityDto> response = cityController.getCity(34l);

        Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);
        Assertions.assertEquals(response.getBody().getId(), 34l);
    }


    }
