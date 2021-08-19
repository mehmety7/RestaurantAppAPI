package com.finartz.restaurantapp.model.converter.dtoconverter;

import com.finartz.restaurantapp.model.dto.MealDto;
import com.finartz.restaurantapp.model.dto.MenuDto;
import com.finartz.restaurantapp.model.entity.MealEntity;
import com.finartz.restaurantapp.model.entity.MenuEntity;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class MenuDtoConverterTest {

    @Spy
    @InjectMocks
    private MenuDtoConverter menuDtoConverter;

    @Mock
    private MealDtoConverter mealDtoConverter;

    @Test
    public void whenPassValidMenuEntity_thenReturnMenuDto(){
        MenuEntity menuEntity = MenuEntity.builder()
                .id(1l)
                .build();

        MealEntity mealEntity = MealEntity.builder().build();
        MealDto mealDto = MealDto.builder().build();

        Mockito.when(mealDtoConverter.convert(mealEntity)).thenReturn(mealDto);
        MenuDto menuDto = menuDtoConverter.convert(menuEntity);

        Assertions.assertEquals(menuDto.getId(), menuEntity.getId());

    }

}