package com.finartz.restaurantapp.controller;

import com.finartz.restaurantapp.model.Meal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/meal/")
public class MealController {

    @Autowired
    private MealService mealService;

    @PostMapping("create")
    public ResponseEntity<Meal> create(@RequestBody Meal meal){
        return new ResponseEntity<>(mealService.create(meal), HttpStatus.CREATED);
    }

    @PutMapping("update")
    public ResponseEntity<Meal> update(@RequestBody Meal meal){
        return new ResponseEntity<>(mealService.update(meal), HttpStatus.OK);
    }

    @GetMapping("get/{id}")
    public ResponseEntity<Meal> get(@PathVariable Long id){
        return new ResponseEntity<>(mealService.getById(id), HttpStatus.OK);
    }

    @GetMapping("getAll")
    public ResponseEntity<List<Meal>> getAll(){
        return new ResponseEntity<>(mealService.getAll(), HttpStatus.OK)
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id){
        mealService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
