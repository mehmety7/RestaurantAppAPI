package com.finartz.restaurantapp.controller;

import com.finartz.restaurantapp.model.entity.CountyEntity;
import com.finartz.restaurantapp.service.CountyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("county")
public class CountyController {

    private final CountyService countyService;

    public CountyController(CountyService countyService) {
        this.countyService = countyService;
    }

    @GetMapping("{id}")
    public ResponseEntity<CountyEntity> getCounty(@PathVariable Long id){
        return new ResponseEntity(countyService.getCounty(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<CountyEntity>> getCounties(){
        return new ResponseEntity(countyService.getCounties(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CountyEntity> createCounty(@RequestBody CountyEntity countyEntity){
        return new ResponseEntity(countyService.createCounty(countyEntity), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<CountyEntity> updateCounty(@RequestBody CountyEntity countyEntity){
        return new ResponseEntity(countyService.updateCounty(countyEntity), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<CountyEntity> deleteCounty(@PathVariable Long id){
        countyService.deleteCounty(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
