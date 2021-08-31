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
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.ArgumentMatchers.anyLong;

@RunWith(SpringRunner.class)
@SpringBootTest(properties = "spring.main.banner-mode=off")
@TestPropertySource("classpath:application-test.properties")
public class MealControllerIntegrationTest {

    @Autowired
    private MealController mealController;

    @MockBean
    private TokenService tokenService;

    @BeforeEach
    public void init(){
        Mockito.when(tokenService.isRequestOwnerAuthoritative(anyLong())).thenReturn(true);
    }

    @Test
    public void whenGetMealById_thenReturnMeal(){
        ResponseEntity<MealDto> response = mealController.getMeal(1l);

        Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);
        Assertions.assertEquals(response.getBody().getId(), 1l);

    }

    @Test
    public void whenCreateNewMeal_thenReturnMeal() {
        MealCreateRequest mealCreateRequest = MealCreateRequest
                .builder()
                .name("Meal")
                .price(10.55)
                .itemIds(null)
                .menuId(1l)
                .build();

        ResponseEntity<MealDto> response = mealController.createMeal(mealCreateRequest);

        Assertions.assertEquals(response.getStatusCode(), HttpStatus.CREATED);
    }

}
