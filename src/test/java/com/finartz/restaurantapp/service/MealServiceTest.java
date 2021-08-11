package com.finartz.restaurantapp.service;

import com.finartz.restaurantapp.model.converter.dtoconverter.MealDtoConverter;
import com.finartz.restaurantapp.model.converter.entityconverter.fromCreateRequest.MealCreateRequestToEntityConverter;
import com.finartz.restaurantapp.model.dto.MealDto;
import com.finartz.restaurantapp.model.entity.MealEntity;
import com.finartz.restaurantapp.model.request.create.MealCreateRequest;
import com.finartz.restaurantapp.repository.MealRepository;
import com.finartz.restaurantapp.service.impl.MealServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class MealServiceTest {

    private static final String NAME_FIRSAT = "Fırsat Menü";

    @InjectMocks
    private MealServiceImpl mealService;

    @Mock
    private MealRepository mealRepository;

    @Mock
    private MealDtoConverter mealDtoConverter;

    @Mock
    private MealCreateRequestToEntityConverter mealCreateRequestToEntityConverter;


    @Test
    public void whenFetchById_thenReturnMeal() {
        MealEntity mealEntity = MealEntity.builder().name(NAME_FIRSAT).build();
        MealDto meal = MealDto.builder().name(NAME_FIRSAT).build();

        Mockito.when(mealRepository.getById(1L)).thenReturn(mealEntity);
        Mockito.when(mealDtoConverter.convert(mealEntity)).thenReturn(meal);

        MealDto resultMeal = mealService.getMeal(1L);

        assertEquals(meal, resultMeal);
    }

    @Test
    public void whenAddMeal_thenReturnSavedMeal() {
        MealEntity mealEntity = MealEntity.builder().name(NAME_FIRSAT).build();
        MealDto meal = MealDto.builder().name(NAME_FIRSAT).build();
        MealCreateRequest mealCreateRequest = MealCreateRequest.builder().name(NAME_FIRSAT).build();

        Mockito.when(mealCreateRequestToEntityConverter.convert(mealCreateRequest)).thenReturn(mealEntity);
        Mockito.when(mealRepository.save(mealEntity)).thenReturn(mealEntity);
        Mockito.when(mealDtoConverter.convert(mealEntity)).thenReturn(meal);

        MealDto resultMeal = mealService.createMeal(mealCreateRequest);

        assertEquals(meal.getName(), resultMeal.getName());
    }

}