package com.finartz.restaurantapp.service;

import com.finartz.restaurantapp.model.dto.MealDto;
import com.finartz.restaurantapp.model.request.create.MealCreateRequest;

public interface MealService {

    MealDto getMeal(Long id);

    MealDto createMeal(MealCreateRequest mealCreateRequest);

}
