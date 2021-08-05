package com.finartz.restaurantapp.controller;

import com.finartz.restaurantapp.model.entity.CityEntity;
import com.finartz.restaurantapp.service.CityService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("city")
public class CityController {

    private final CityService cityService;

    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    @GetMapping("{id}")
    public ResponseEntity<CityEntity> getCity(@PathVariable Long id){
        return new ResponseEntity(cityService.getCity(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<CityEntity>> getCities(){
        return new ResponseEntity(cityService.getCities(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CityEntity> createCity(@RequestBody CityEntity cityEntity){
        return new ResponseEntity(cityService.createCity(cityEntity), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<CityEntity> updateCity(@RequestBody CityEntity cityEntity){
        return new ResponseEntity(cityService.updateCity(cityEntity), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<CityEntity> deleteCity(@PathVariable Long id){
        cityService.deleteCity(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
