package com.finartz.restaurantapp.model.converter.dtoconverter;

import com.finartz.restaurantapp.exception.EntityNotFoundException;
import com.finartz.restaurantapp.model.dto.ItemDto;
import com.finartz.restaurantapp.model.dto.MealDto;
import com.finartz.restaurantapp.model.entity.ItemEntity;
import com.finartz.restaurantapp.model.entity.MealEntity;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Collections;

@RunWith(MockitoJUnitRunner.class)
public class MealDtoConverterTest {

    @Spy
    @InjectMocks
    private MealDtoConverter mealDtoConverter;

    @Mock
    private ItemDtoConverter itemDtoConverter;

    @Test
    public void whenPassValidMealEntity_thenReturnMealDto(){
        ItemEntity itemEntity = ItemEntity.builder().build();
        ItemDto itemDto = ItemDto.builder().build();

        MealEntity mealEntity = MealEntity.builder()
                .id(1L)
                .name("Meal")
                .itemEntities(Collections.singletonList(itemEntity))
                .build();

        Mockito.when(itemDtoConverter.convert(itemEntity)).thenReturn(itemDto);
        MealDto mealDto = mealDtoConverter.convert(mealEntity);

        Assertions.assertEquals(mealDto.getName(), mealEntity.getName());

    }

    @Test(expected = EntityNotFoundException.class)
    public void whenPassNullMealEntity_thenReturnThrowEntityNotFoundException(){
        mealDtoConverter.convert(null);
    }

}
