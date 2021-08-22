package com.finartz.restaurantapp.controller;

import com.finartz.restaurantapp.model.dto.MealDto;
import com.finartz.restaurantapp.model.request.create.MealCreateRequest;
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
public class MealControllerIntergationTest {

    @Autowired
    private MealController mealController;

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
        Assertions.assertEquals(response.getBody().getName(), "Meal");
    }

}
