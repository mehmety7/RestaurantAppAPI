package com.finartz.restaurantapp.controller;

import com.finartz.restaurantapp.model.entity.BranchEntity;
import com.finartz.restaurantapp.model.entity.RestaurantEntity;
import com.finartz.restaurantapp.model.enumerated.Status;
import com.finartz.restaurantapp.service.RestaurantService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("restaurant")
public class RestaurantController {

    private final RestaurantService restaurantService;

    public RestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @GetMapping
    public ResponseEntity<List<RestaurantEntity>> getRestaurants(){
        return new ResponseEntity(restaurantService.getRestaurants(), HttpStatus.OK);
    }

    @GetMapping("waiting")
    public ResponseEntity<List<BranchEntity>> getRestaurants(Status status){
        return new ResponseEntity(restaurantService.getRestaurants(status), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<RestaurantEntity> getRestaurant(@PathVariable Long id){
        return new ResponseEntity(restaurantService.getRestaurant(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<RestaurantEntity> createRestaurant(@RequestBody RestaurantEntity restaurantEntity){
        return new ResponseEntity(restaurantService.createRestaurant(restaurantEntity), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<RestaurantEntity> updateRestaurant(@RequestBody RestaurantEntity restaurantEntity){
        return new ResponseEntity(restaurantService.updateRestaurant(restaurantEntity), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteRestaurant(@PathVariable Long id){
        restaurantService.deleteRestaurant(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
