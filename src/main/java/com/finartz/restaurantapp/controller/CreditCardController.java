package com.finartz.restaurantapp.controller;

import com.finartz.restaurantapp.model.CreditCard;
import com.finartz.restaurantapp.service.CreditCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("creditcard")
public class CreditCardController {

    @Autowired
    private CreditCardService creditcardService;

    @PostMapping
    public ResponseEntity<CreditCard> create(@RequestBody CreditCard creditcard){
        return new ResponseEntity(creditcardService.create(creditcard), HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<CreditCard> get(@PathVariable Long id){
        return new ResponseEntity(creditcardService.getById(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<CreditCard>> getAll(){
        return new ResponseEntity(creditcardService.getAll(), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<CreditCard> update(@RequestBody CreditCard creditcard){
        return new ResponseEntity(creditcardService.update(creditcard), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<CreditCard> deleteById(@PathVariable Long id){
        creditcardService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
