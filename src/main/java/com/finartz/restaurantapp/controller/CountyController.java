package com.finartz.restaurantapp.controller;

import com.finartz.restaurantapp.model.County;
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

    @PostMapping
    public ResponseEntity<County> create(@RequestBody County county){
        return new ResponseEntity(countyService.create(county), HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<County> get(@PathVariable Long id){
        return new ResponseEntity(countyService.getById(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<County>> getAll(){
        return new ResponseEntity(countyService.getAll(), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<County> update(@RequestBody County county){
        return new ResponseEntity(countyService.update(county), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<County> deleteById(@PathVariable Long id){
        countyService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
