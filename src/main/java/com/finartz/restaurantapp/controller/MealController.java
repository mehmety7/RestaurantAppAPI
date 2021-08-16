package com.finartz.restaurantapp.controller;

import com.finartz.restaurantapp.model.dto.MealDto;
import com.finartz.restaurantapp.model.request.create.MealCreateRequest;
import com.finartz.restaurantapp.service.MealService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("meal")
public class MealController {

    private final MealService mealService;

    public MealController(MealService mealService) {
        this.mealService = mealService;
    }

    @GetMapping("{id}")
    public ResponseEntity<MealDto> getMeal(@PathVariable Long id){
        return new ResponseEntity(mealService.getMeal(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<MealDto> createMeal(@Valid @RequestBody MealCreateRequest mealCreateRequest){
        return new ResponseEntity(mealService.createMeal(mealCreateRequest), HttpStatus.CREATED);
    }

}