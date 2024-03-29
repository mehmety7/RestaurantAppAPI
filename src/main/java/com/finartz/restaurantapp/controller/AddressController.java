package com.finartz.restaurantapp.controller;

import com.finartz.restaurantapp.model.dto.AddressDto;
import com.finartz.restaurantapp.model.request.create.AddressCreateRequest;
import com.finartz.restaurantapp.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("address")
@RequiredArgsConstructor
public class AddressController {

    private final AddressService addressService;

    @GetMapping("{id}")
    public ResponseEntity<AddressDto> getAddress(@PathVariable Long id){
        return new ResponseEntity<>(addressService.getAddress(id), HttpStatus.OK);
    }

    @GetMapping("user")
    public ResponseEntity<List<AddressDto>> getUserAddresses(@RequestParam("user_id") Long userId){
        return new ResponseEntity<>(addressService.getUserAddresses(userId), HttpStatus.OK);
    }

    @GetMapping("branch")
    public ResponseEntity<AddressDto> getBranchAddress(@RequestParam("branch_id") Long branchId) {
        return new ResponseEntity<>(addressService.getBranchAddress(branchId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<AddressDto> createAddress(@Valid @RequestBody AddressCreateRequest addressCreateRequest){
        addressCreateRequest.setFirst(false);
        return new ResponseEntity<>(addressService.createAddress(addressCreateRequest), HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<AddressDto> setActiveAddress(@PathVariable Long id){
        addressService.setActiveAddress(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
