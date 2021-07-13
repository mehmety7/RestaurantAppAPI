package com.finartz.restaurantapp.controller;

import com.finartz.restaurantapp.model.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/item/")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @PostMapping("create")
    public ResponseEntity<Item> create(@RequestBody Item item){
        return new ResponseEntity<>(itemService.create(item), HttpStatus.CREATED);
    }

    @PutMapping("update")
    public ResponseEntity<Item> update(@RequestBody Item item){
        return new ResponseEntity<>(itemService.update(item), HttpStatus.OK);
    }

    @GetMapping("get/{id}")
    public ResponseEntity<Item> get(@PathVariable Long id){
        return new ResponseEntity<>(itemService.getById(id), HttpStatus.OK);
    }

    @GetMapping("getAll")
    public ResponseEntity<List<Item>> getAll(){
        return new ResponseEntity<>(itemService.getAll(), HttpStatus.OK)
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id){
        itemService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
