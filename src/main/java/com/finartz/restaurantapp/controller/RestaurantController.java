package com.finartz.restaurantapp.controller;

import com.finartz.restaurantapp.model.dto.RestaurantDto;
import com.finartz.restaurantapp.model.enumerated.Status;
import com.finartz.restaurantapp.model.request.create.RestaurantCreateRequest;
import com.finartz.restaurantapp.model.request.update.RestaurantUpdateRequest;
import com.finartz.restaurantapp.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("restaurant")
@RequiredArgsConstructor
public class RestaurantController {

    private final RestaurantService restaurantService;

    @GetMapping("waiting")
    public ResponseEntity<List<RestaurantDto>> getRestaurants(){
        return new ResponseEntity(restaurantService.getRestaurants(Status.WAITING), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<RestaurantDto> getRestaurant(@PathVariable Long id){
        return new ResponseEntity(restaurantService.getRestaurant(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<RestaurantDto> createRestaurant(@Valid @RequestBody RestaurantCreateRequest restaurantCreateRequest){
        return new ResponseEntity(restaurantService.createRestaurant(restaurantCreateRequest), HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<RestaurantDto> updateRestaurant(@PathVariable Long id, @RequestBody RestaurantUpdateRequest restaurantUpdateRequest){
        return new ResponseEntity(restaurantService.updateRestaurant(id, restaurantUpdateRequest), HttpStatus.OK);
    }

}
