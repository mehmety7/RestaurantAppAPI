package com.finartz.restaurantapp.service.impl;

import com.finartz.restaurantapp.model.entity.MealEntity;
import com.finartz.restaurantapp.repository.MealRepository;
import com.finartz.restaurantapp.service.MealService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MealServiceImpl implements MealService {

    private final MealRepository mealRepository;

    public MealServiceImpl(MealRepository mealRepository) {
        this.mealRepository = mealRepository;
    }

    @Override
    public List<MealEntity> getMeals(){
        return mealRepository.findAll();
    }

    @Override
    public MealEntity getMeal(Long id){
        return mealRepository.getById(id);
    }

    @Override
    public MealEntity createMeal(MealEntity mealEntity){
        return mealRepository.save(mealEntity);
    }

    @Override
    public MealEntity updateMeal(MealEntity mealEntity){
        MealEntity foundMealEntity = mealRepository.getById(mealEntity.getId());

        if(mealEntity.getName() != null)
            foundMealEntity.setName(mealEntity.getName());
        if(mealEntity.getPrice() != null)
            foundMealEntity.setPrice(mealEntity.getPrice());
        if(mealEntity.getMenuEntity() != null)
            foundMealEntity.setMenuEntity(mealEntity.getMenuEntity());
        if(mealEntity.getItemEntities() != null)
            foundMealEntity.setItemEntities(mealEntity.getItemEntities());

        return mealRepository.save(foundMealEntity);
    }

    @Override
    public MealEntity deleteMeal(Long id){
        MealEntity mealEntity = mealRepository.getById(id);
        if (mealEntity != null) {
            mealRepository.deleteById(id);
            return mealEntity;
        }
        return mealEntity;
    }

}
