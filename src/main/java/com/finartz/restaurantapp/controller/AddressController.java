package com.finartz.restaurantapp.controller;

import com.finartz.restaurantapp.model.Address;
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

    @PostMapping
    public ResponseEntity<Address> create(@RequestBody Address address){
        return new ResponseEntity(addressService.create(address), HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<Address> get(@PathVariable Long id){
        return new ResponseEntity(addressService.getById(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Address>> getAll(){
        return new ResponseEntity(addressService.getAll(), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Address> update(@RequestBody Address address){
        return new ResponseEntity(addressService.update(address), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Address> deleteById(@PathVariable Long id){
        addressService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
