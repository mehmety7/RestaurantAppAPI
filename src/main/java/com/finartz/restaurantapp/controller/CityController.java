package com.finartz.restaurantapp.controller;

import com.finartz.restaurantapp.model.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/city/")
public class CityController {

    @Autowired
    private CityService cityService;

    @PostMapping("create")
    public ResponseEntity<City> create(@RequestBody City city){
        return new ResponseEntity<>(cityService.create(city), HttpStatus.CREATED);
    }

    @PutMapping("update")
    public ResponseEntity<City> update(@RequestBody City city){
        return new ResponseEntity<>(cityService.update(city), HttpStatus.OK);
    }

    @GetMapping("get/{id}")
    public ResponseEntity<City> get(@PathVariable Long id){
        return new ResponseEntity<>(cityService.getById(id), HttpStatus.OK);
    }

    @GetMapping("getAll")
    public ResponseEntity<List<City>> getAll(){
        return new ResponseEntity<>(cityService.getAll(), HttpStatus.OK)
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id){
        cityService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
