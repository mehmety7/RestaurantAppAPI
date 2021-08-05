package com.finartz.restaurantapp.controller;

import com.finartz.restaurantapp.model.dto.AddressDto;
import com.finartz.restaurantapp.model.entity.AddressEntity;
import com.finartz.restaurantapp.model.request.AddressRequest;
import com.finartz.restaurantapp.service.AddressService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping
    public ResponseEntity<List<AddressDto>> getAddresses(){
        return new ResponseEntity(addressService.getAddresses(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<AddressDto> createAddress(@RequestBody AddressRequest addressRequest){
        return new ResponseEntity(addressService.createAddress(addressRequest), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<AddressEntity> updateAddress(@RequestBody AddressRequest addressRequest){
        return new ResponseEntity(addressService.updateAddress(addressRequest), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteAddress(@PathVariable Long id){
        addressService.deleteAddress(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
