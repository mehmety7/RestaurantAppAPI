package com.finartz.restaurantapp.controller;

import com.finartz.restaurantapp.model.dto.CityDto;
import com.finartz.restaurantapp.service.CityService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("city")
public class CityController {

    private final CityService cityService;

    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    @GetMapping("{id}")
    public ResponseEntity<CityDto> getCity(@PathVariable Long id){
        return new ResponseEntity(cityService.getCity(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<CityDto>> getCities(){
        return new ResponseEntity(cityService.getCities(), HttpStatus.OK);
    }

}
