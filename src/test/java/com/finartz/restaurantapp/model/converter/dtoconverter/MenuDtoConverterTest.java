package com.finartz.restaurantapp.model.converter.dtoconverter;

import com.finartz.restaurantapp.exception.EntityNotFoundException;
import com.finartz.restaurantapp.model.dto.MealDto;
import com.finartz.restaurantapp.model.dto.MenuDto;
import com.finartz.restaurantapp.model.entity.BranchEntity;
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

import java.util.Arrays;

@RunWith(MockitoJUnitRunner.class)
public class MenuDtoConverterTest {

    @Spy
    @InjectMocks
    private MenuDtoConverter menuDtoConverter;

    @Mock
    private MealDtoConverter mealDtoConverter;

    @Test
    public void whenPassValidMenuEntity_thenReturnMenuDto(){
        MealEntity mealEntity = MealEntity.builder().build();
        MealDto mealDto = MealDto.builder().build();

        MenuEntity menuEntity = MenuEntity.builder()
                .id(1l)
                .mealEntities(Arrays.asList(MealEntity.builder().build()))
                .branchEntity(BranchEntity.builder().id(1l).build())
                .build();


        Mockito.when(mealDtoConverter.convert(mealEntity)).thenReturn(mealDto);
        MenuDto menuDto = menuDtoConverter.convert(menuEntity);

        Assertions.assertEquals(menuDto.getId(), menuEntity.getId());

    }

    @Test(expected = EntityNotFoundException.class)
    public void whenPassNullMenuEntity_thenReturnThrowEntityNotFoundException(){
        menuDtoConverter.convert(null);
    }

}