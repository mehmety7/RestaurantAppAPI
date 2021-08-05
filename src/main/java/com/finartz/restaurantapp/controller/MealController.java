package com.finartz.restaurantapp.controller;

import com.finartz.restaurantapp.model.entity.MealEntity;
import com.finartz.restaurantapp.service.MealService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("meal")
public class MealController {

    private final MealService mealService;

    public MealController(MealService mealService) {
        this.mealService = mealService;
    }

    @GetMapping("{id}")
    public ResponseEntity<MealEntity> getMeal(@PathVariable Long id){
        return new ResponseEntity(mealService.getMeal(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<MealEntity>> getMeals(){
        return new ResponseEntity(mealService.getMeals(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<MealEntity> createMeal(@RequestBody MealEntity mealEntity){
        return new ResponseEntity(mealService.createMeal(mealEntity), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<MealEntity> updateMeal(@RequestBody MealEntity mealEntity){
        return new ResponseEntity(mealService.updateMeal(mealEntity), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<MealEntity> deleteMeal(@PathVariable Long id){
        mealService.deleteMeal(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}