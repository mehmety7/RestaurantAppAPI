package com.finartz.restaurantapp.service;

import com.finartz.restaurantapp.model.Meal;

import java.util.List;

public interface MealService {

    public Meal create(Meal meal);

    public List<Meal> getAll();

    public Meal getById(Long id);

    public Meal update(Meal meal);

    public Meal deleteById(Long id);

}
