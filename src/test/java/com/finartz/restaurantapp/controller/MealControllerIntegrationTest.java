package com.finartz.restaurantapp.controller;

import com.finartz.restaurantapp.model.dto.MealDto;
import com.finartz.restaurantapp.model.request.create.MealCreateRequest;
import com.finartz.restaurantapp.service.TokenService;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Objects;

import static org.mockito.ArgumentMatchers.anyLong;

@RunWith(SpringRunner.class)
@SpringBootTest(properties = "spring.main.banner-mode=off")
@ActiveProfiles("test")
public class MealControllerIntegrationTest {

    @Autowired
    private MealController mealController;

    @MockBean
    private TokenService tokenService;

    @BeforeEach
    public void init(){
        Mockito.doNothing().when(tokenService).checkRequestOwnerAuthoritative(anyLong());
    }

    @Test
    public void whenGetMealById_thenReturnMeal(){
        ResponseEntity<MealDto> response = mealController.getMeal(1L);

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(1L, Objects.requireNonNull(response.getBody()).getId());

    }

    @Test
    public void whenCreateNewMeal_thenReturnMeal() {
        MealCreateRequest mealCreateRequest = MealCreateRequest
                .builder()
                .name("Meal")
                .price(10.55)
                .itemIds(null)
                .menuId(1L)
                .build();

        ResponseEntity<MealDto> response = mealController.createMeal(mealCreateRequest);

        Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

}
