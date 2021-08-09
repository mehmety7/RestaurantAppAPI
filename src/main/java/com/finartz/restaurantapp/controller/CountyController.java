package com.finartz.restaurantapp.controller;

import com.finartz.restaurantapp.model.dto.CountyDto;
import com.finartz.restaurantapp.service.CountyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("county")
public class CountyController {

    private final CountyService countyService;

    public CountyController(CountyService countyService) {
        this.countyService = countyService;
    }

    @GetMapping("{id}")
    public ResponseEntity<CountyDto> getCounty(@PathVariable Long id){
        return new ResponseEntity(countyService.getCounty(id), HttpStatus.OK);
    }

}
