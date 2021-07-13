package com.finartz.restaurantapp.controller;

import com.finartz.restaurantapp.model.County;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/county/")
public class CountyController {

    @Autowired
    private CountyService countyService;

    @PostMapping("create")
    public ResponseEntity<County> create(@RequestBody County county){
        return new ResponseEntity<>(countyService.create(county), HttpStatus.CREATED);
    }

    @PutMapping("update")
    public ResponseEntity<County> update(@RequestBody County county){
        return new ResponseEntity<>(countyService.update(county), HttpStatus.OK);
    }

    @GetMapping("get/{id}")
    public ResponseEntity<County> get(@PathVariable Long id){
        return new ResponseEntity<>(countyService.getById(id), HttpStatus.OK);
    }

    @GetMapping("getAll")
    public ResponseEntity<List<County>> getAll(){
        return new ResponseEntity<>(countyService.getAll(), HttpStatus.OK)
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id){
        countyService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
