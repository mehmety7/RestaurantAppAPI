package com.finartz.restaurantapp.model.converter.dtoconverter;

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

@RunWith(MockitoJUnitRunner.class)
public class MealDtoConverterTest {

    @Spy
    @InjectMocks
    private MealDtoConverter mealDtoConverter;

    @Mock
    private ItemDtoConverter itemDtoConverter;

    @Test
    public void whenPassValidMealEntity_thenReturnMealDto(){
        MealEntity mealEntity = MealEntity.builder()
                .id(1l)
                .name("Meal")
                .build();

        ItemEntity itemEntity = ItemEntity.builder().build();
        ItemDto itemDto = ItemDto.builder().build();

        Mockito.when(itemDtoConverter.convert(itemEntity)).thenReturn(itemDto);
        MealDto mealDto = mealDtoConverter.convert(mealEntity);

        Assertions.assertEquals(mealDto.getName(), mealEntity.getName());

    }

}
