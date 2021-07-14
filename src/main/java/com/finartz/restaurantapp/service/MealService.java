package com.finartz.restaurantapp.service;

import com.finartz.restaurantapp.model.Meal;
import com.finartz.restaurantapp.repository.MealRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MealService {

    private final MealRepository mealRepository;

    public MealService(MealRepository mealRepository) {
        this.mealRepository = mealRepository;
    }

    public Meal create(Meal meal){
        return mealRepository.save(meal);
    }

    public List<Meal> getAll(){
        return mealRepository.findAll();
    }

    public Meal getById(Long id){
        return mealRepository.getById(id);
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
