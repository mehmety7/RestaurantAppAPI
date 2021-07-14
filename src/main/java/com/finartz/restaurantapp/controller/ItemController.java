package com.finartz.restaurantapp.controller;

import com.finartz.restaurantapp.model.Item;
import com.finartz.restaurantapp.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("item")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @PostMapping
    public ResponseEntity<Item> create(@RequestBody Item item){
        return new ResponseEntity(itemService.create(item), HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<Item> get(@PathVariable Long id){
        return new ResponseEntity(itemService.getById(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Item>> getAll(){
        return new ResponseEntity(itemService.getAll(), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Item> update(@RequestBody Item item){
        return new ResponseEntity(itemService.update(item), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Item> deleteById(@PathVariable Long id){
        itemService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
