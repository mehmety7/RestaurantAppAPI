package com.finartz.restaurantapp.service;

import com.finartz.restaurantapp.model.Meal;
import com.finartz.restaurantapp.repository.MealRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MealService {

    @Autowired
    private MealRepository mealRepository;

    public Meal create(Meal meal){
        Meal save = mealRepository.save(meal);
        return save;
    }

    public List<Meal> getAll(){
        List<Meal> meals = mealRepository.findAll();
        return meals;
    }

    public Meal getById(Long id){
        Meal meal = mealRepository.getById(id);
        return meal;
    }

    public Meal update(Meal meal){
        Meal update = mealRepository.getById(meal.getId());
        if(update != null) {
            mealRepository.save(meal);
            return update;
        }
        return meal;
    }

    public Meal deleteById(Long id){
        Meal meal = mealRepository.getById(id);
        if (meal != null) {
            mealRepository.deleteById(id);
            return meal;
        }
        return meal;
    }
}
