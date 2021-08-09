package com.finartz.restaurantapp.controller;

import com.finartz.restaurantapp.exception.ResourceNotFoundException;
import com.finartz.restaurantapp.model.dto.ItemDto;
import com.finartz.restaurantapp.model.request.create.ItemCreateRequest;
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
    public ResponseEntity<ItemDto> getItem(@PathVariable Long id){
        ItemDto item;
        try{
            item = itemService.getItem(id);
        }catch (ResourceNotFoundException ex){
            throw new ResourceNotFoundException("Not found Item with id: " + id);
        }
        return new ResponseEntity<>(item, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<ItemDto>> getItems(@RequestParam(defaultValue = "0") Integer pageNo,
                                                  @RequestParam(defaultValue = "10") Integer pageSize,
                                                  @RequestParam(defaultValue = "id") String sortBy){
        return new ResponseEntity(itemService.getItems(pageNo, pageSize, sortBy), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ItemDto> createItem(@RequestBody ItemCreateRequest itemCreateRequest){
        return new ResponseEntity(itemService.createItem(itemCreateRequest), HttpStatus.CREATED);
    }

}