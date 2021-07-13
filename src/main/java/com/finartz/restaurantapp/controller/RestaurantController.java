package com.finartz.restaurantapp.controller;

import com.finartz.restaurantapp.model.Restaurant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/restaurant/")
public class RestaurantController {

    @Autowired
    private RestaurantService restaurantService;

    @PostMapping("create")
    public ResponseEntity<Restaurant> create(@RequestBody Restaurant restaurant){
        return new ResponseEntity<>(restaurantService.create(restaurant), HttpStatus.CREATED);
    }

    @PutMapping("update")
    public ResponseEntity<Restaurant> update(@RequestBody Restaurant restaurant){
        return new ResponseEntity<>(restaurantService.update(restaurant), HttpStatus.OK);
    }

    @GetMapping("get/{id}")
    public ResponseEntity<Restaurant> get(@PathVariable Long id){
        return new ResponseEntity<>(restaurantService.getById(id), HttpStatus.OK);
    }

    @GetMapping("getAll")
    public ResponseEntity<List<Restaurant>> getAll(){
        return new ResponseEntity(restaurantService.getAll(), HttpStatus.OK);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id){
        restaurantService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
