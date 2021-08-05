package com.finartz.restaurantapp.controller;

import com.finartz.restaurantapp.model.entity.ItemEntity;
import com.finartz.restaurantapp.service.ItemService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("item")
public class ItemController {

    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping("{id}")
    public ResponseEntity<ItemEntity> getItem(@PathVariable Long id){
        return new ResponseEntity(itemService.getItem(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<ItemEntity>> getItems(){
        return new ResponseEntity(itemService.getItems(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ItemEntity> createItem(@RequestBody ItemEntity itemEntity){
        return new ResponseEntity(itemService.createItem(itemEntity), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<ItemEntity> updateItem(@RequestBody ItemEntity itemEntity){
        return new ResponseEntity(itemService.updateItem(itemEntity), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<ItemEntity> deleteItem(@PathVariable Long id){
        itemService.deleteItem(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}