package com.finartz.restaurantapp.controller;

import com.finartz.restaurantapp.model.dto.AddressDto;
import com.finartz.restaurantapp.model.request.create.AddressCreateRequest;
import com.finartz.restaurantapp.service.AddressService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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

    @GetMapping("user")
    public ResponseEntity<List<AddressDto>> getUserAddresses(@RequestParam Long user_id){
        return new ResponseEntity(addressService.getUserAddresses(user_id), HttpStatus.OK);
    }

    @GetMapping("branch")
    public ResponseEntity<AddressDto> getBranchAddress(@RequestParam Long branch_id) {
        return new ResponseEntity(addressService.getBranchAddress(branch_id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<AddressDto> createAddress(@Valid @RequestBody AddressCreateRequest addressCreateRequest){
        return new ResponseEntity(addressService.createAddress(addressCreateRequest), HttpStatus.CREATED);
    }

}
