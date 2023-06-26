package com.finartz.restaurantapp.controller;

import com.finartz.restaurantapp.model.dto.CountyDto;
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
@SpringBootTest("spring.main.banner-mode=off")
@ActiveProfiles("test")
public class CountyControllerIntegrationTest {

    @Autowired
    private CountyController countyController;

    @Test
    public void whenGetCountyById_thenReturnCounty(){
        ResponseEntity<CountyDto> response = countyController.getCounty(855L);

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(855L, Objects.requireNonNull(response.getBody()).getId());
    }

    @Test
    public void whenGetCountyByCityId_thenReturnCounties(){
        ResponseEntity<List<CountyDto>> response = countyController.getCounties(34L);

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(34L, Objects.requireNonNull(response.getBody()).get(0).getCityId());
    }
}
