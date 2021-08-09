package com.finartz.restaurantapp.controller;

import com.finartz.restaurantapp.model.dto.AddressDto;
import com.finartz.restaurantapp.model.request.create.AddressCreateRequest;
import com.finartz.restaurantapp.service.AddressService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("address")
public class AddressController {

    private final AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @GetMapping("{id}")
    public ResponseEntity<AddressDto> getAddress(@PathVariable Long id){
        return new ResponseEntity(addressService.getAddress(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<AddressDto> createAddress(@RequestBody AddressCreateRequest addressCreateRequest){
        return new ResponseEntity(addressService.createAddress(addressCreateRequest), HttpStatus.CREATED);
    }

}
