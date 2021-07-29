package com.finartz.restaurantapp.service;

import com.finartz.restaurantapp.model.Meal;
import com.finartz.restaurantapp.repository.MealRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class MealServiceTest {

    @InjectMocks
    private MealService mealService;

    @Mock
    private MealRepository mealRepository;


    @Test
    public void whenFetchAll_thenReturnAllMeal() {
        Meal meal1 = Meal.builder().id(1l).name("Efsane Fırsat Menüsü").build();
        Meal meal2 = Meal.builder().id(2l).name("Avcılar").build();
        List<Meal> mealList = Arrays.asList(meal1, meal2);

        Mockito.when(mealRepository.findAll()).thenReturn(mealList);

        List<Meal> fetchedList = mealService.getAll();

        assertEquals(mealList.size(), fetchedList.size());
    }

    @Test
    public void whenFetchById_thenReturnMeal() {
        Meal meal = Meal.builder().name("Efsane Fırsat Menüsü").build();

        Mockito.when(mealRepository.getById(1L)).thenReturn(meal);

        Meal fetchedMeal = mealService.getById(1L);

        assertEquals(meal.getId(), fetchedMeal.getId());
    }

    @Test
    public void whenAddMeal_thenReturnSavedMeal() {
        Meal meal = Meal.builder().name("Efsane Fırsat Menüsü").build();

        Mockito.doReturn(meal).when(mealRepository).save(meal);

        Meal returnedMeal = mealService.create(meal);

        assertEquals(meal.getName(), returnedMeal.getName());
    }

}