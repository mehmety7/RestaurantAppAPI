package com.finartz.restaurantapp.controller;

import com.finartz.restaurantapp.model.City;
import com.finartz.restaurantapp.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("city")
public class CityController {

    @Autowired
    private CityService cityService;

    @PostMapping
    public ResponseEntity<City> create(@RequestBody City city){
        return new ResponseEntity(cityService.create(city), HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<City> get(@PathVariable Long id){
        return new ResponseEntity(cityService.getById(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<City>> getAll(){
        return new ResponseEntity(cityService.getAll(), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<City> update(@RequestBody City city){
        return new ResponseEntity(cityService.update(city), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<City> deleteById(@PathVariable Long id){
        cityService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
