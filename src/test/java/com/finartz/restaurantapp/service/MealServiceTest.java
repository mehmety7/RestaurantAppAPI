package com.finartz.restaurantapp.service;

import com.finartz.restaurantapp.model.Meal;
import com.finartz.restaurantapp.repository.MealRepository;
import com.finartz.restaurantapp.service.impl.MealServiceImpl;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
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

    private static final String NAME_FIRSAT = "Fırsat Menü";
    private static final String NAME_EFSANE = "Efsane Menü";


    @InjectMocks
    private MealServiceImpl mealService;

    @Mock
    private MealRepository mealRepository;


    @Test
    public void whenFetchAll_thenReturnAllMeal() {
        Meal meal1 = Meal.builder().id(1l).name(NAME_FIRSAT).build();
        Meal meal2 = Meal.builder().id(2l).name(NAME_EFSANE).build();
        List<Meal> mealList = Arrays.asList(meal1, meal2);

        Mockito.when(mealRepository.findAll()).thenReturn(mealList);

        List<Meal> fetchedList = mealService.getAll();

        assertEquals(mealList.size(), fetchedList.size());
    }

    @Test
    public void whenFetchById_thenReturnMeal() {
        Meal meal = Meal.builder().name(NAME_FIRSAT).build();

        Mockito.when(mealRepository.getById(1L)).thenReturn(meal);

        Meal fetchedMeal = mealService.getById(1L);

        assertEquals(meal.getId(), fetchedMeal.getId());
    }

    @Test
    public void whenAddMeal_thenReturnSavedMeal() {
        Meal meal = Meal.builder().name(NAME_FIRSAT).build();

        Mockito.doReturn(meal).when(mealRepository).save(meal);

        Meal returnedMeal = mealService.create(meal);

        assertEquals(meal.getName(), returnedMeal.getName());
    }

    @Test
    public void whenUpdateMeal_thenReturnUpdatedMeal(){
        Meal foundMeal = Meal.builder().id(1l).name(NAME_FIRSAT).build();
        Meal modifyMeal = Meal.builder().id(1l).name(NAME_EFSANE).build();

        Mockito.when(mealRepository.getById(1l)).thenReturn(foundMeal);
        Mockito.when(mealRepository.save(modifyMeal)).thenReturn(modifyMeal);

        Meal updatedMeal = mealService.update(modifyMeal);

        Assertions.assertNotEquals(updatedMeal.getName(), NAME_FIRSAT);
        Assertions.assertEquals(updatedMeal.getName(), NAME_EFSANE);

    }

}