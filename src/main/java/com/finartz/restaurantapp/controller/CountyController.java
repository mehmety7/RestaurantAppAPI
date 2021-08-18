package com.finartz.restaurantapp.controller;

import com.finartz.restaurantapp.model.dto.CountyDto;
import com.finartz.restaurantapp.service.CountyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("county")
@RequiredArgsConstructor
public class CountyController {

    private final CountyService countyService;

    @GetMapping("{id}")
    public ResponseEntity<CountyDto> getCounty(@PathVariable Long id){
        return new ResponseEntity(countyService.getCounty(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<CountyDto>> getCounties(@RequestParam Long city_id){
        return new ResponseEntity(countyService.getCounties(city_id), HttpStatus.OK);
    }

}
