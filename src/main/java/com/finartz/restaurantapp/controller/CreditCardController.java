package com.finartz.restaurantapp.controller;

import com.finartz.restaurantapp.model.CreditCard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/creditcard")
public class CreditCardController {

    @Autowired
    private CreditCardService cardService;

    @PostMapping("create")
    public ResponseEntity<CreditCard> create(@RequestBody CreditCard card){
        return new ResponseEntity<>(cardService.create(card), HttpStatus.CREATED);
    }

    @PutMapping("update")
    public ResponseEntity<CreditCard> update(@RequestBody CreditCard card){
        return new ResponseEntity<>(cardService.update(card), HttpStatus.OK);
    }

    @GetMapping("get/{id}")
    public ResponseEntity<CreditCard> get(@PathVariable Long id){
        return new ResponseEntity<>(cardService.getById(id), HttpStatus.OK);
    }

    @GetMapping("getAll")
    public ResponseEntity<List<CreditCard>> getAll(){
        return new ResponseEntity<>(cardService.getAll(), HttpStatus.OK)
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id){
        cardService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
