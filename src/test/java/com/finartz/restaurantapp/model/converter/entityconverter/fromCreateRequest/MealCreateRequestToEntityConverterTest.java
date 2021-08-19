package com.finartz.restaurantapp.model.converter.entityconverter.fromCreateRequest;

import com.finartz.restaurantapp.model.entity.MealEntity;
import com.finartz.restaurantapp.model.request.create.MealCreateRequest;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class MealCreateRequestToEntityConverterTest {

    @Spy
    private MealCreateRequestToEntityConverter mealCreateRequestToEntityConverter;

    @Test
    public void whenPassValidMealCreateRequest_thenReturnMealEntity(){
        MealCreateRequest mealCreateRequest = MealCreateRequest.builder()
                .name("Meal")
                .build();

        MealEntity mealEntity = mealCreateRequestToEntityConverter.convert(mealCreateRequest);

        Assertions.assertEquals(mealEntity.getName(), mealCreateRequest.getName());
    }

}
