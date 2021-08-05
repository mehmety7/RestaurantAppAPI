package com.finartz.restaurantapp.service;

import com.finartz.restaurantapp.model.entity.MealEntity;

import java.util.List;

public interface MealService {

    public List<MealEntity> getMeals();

    public MealEntity getMeal(Long id);

    public MealEntity createMeal(MealEntity mealEntity);

    public MealEntity updateMeal(MealEntity mealEntity);

    public MealEntity deleteMeal(Long id);

}
