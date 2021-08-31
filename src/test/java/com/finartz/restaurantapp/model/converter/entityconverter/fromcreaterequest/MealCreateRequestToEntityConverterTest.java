package com.finartz.restaurantapp.model.converter.entityconverter.fromcreaterequest;

import com.finartz.restaurantapp.exception.EntityNotFoundException;
import com.finartz.restaurantapp.model.entity.MealEntity;
import com.finartz.restaurantapp.model.request.create.MealCreateRequest;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;

@RunWith(MockitoJUnitRunner.class)
public class MealCreateRequestToEntityConverterTest {

    @Spy
    private MealCreateRequestToEntityConverter mealCreateRequestToEntityConverter;

    @Test
    public void whenPassValidMealCreateRequest_thenReturnMealEntity(){
        MealCreateRequest mealCreateRequest = MealCreateRequest.builder()
                .name("Meal")
                .itemIds(Arrays.asList(1L, 2L, 3L))
                .menuId(1L)
                .build();

        MealEntity mealEntity = mealCreateRequestToEntityConverter.convert(mealCreateRequest);

        Assertions.assertEquals(mealEntity.getName(), mealCreateRequest.getName());
    }

    @Test(expected = EntityNotFoundException.class)
    public void whenPassNullMealCreateRequest_thenThrowEntityNotFoundException(){
        mealCreateRequestToEntityConverter.convert(null);
    }

}
