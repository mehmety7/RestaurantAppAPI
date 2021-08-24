package com.finartz.restaurantapp.controller;

import com.finartz.restaurantapp.model.dto.ItemDto;
import com.finartz.restaurantapp.model.dto.PageDto;
import com.finartz.restaurantapp.model.request.create.ItemCreateRequest;
import com.finartz.restaurantapp.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("item")
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @GetMapping("{id}")
    public ResponseEntity<ItemDto> getItem(@PathVariable Long id){
        return new ResponseEntity(itemService.getItem(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<PageDto<ItemDto>> getItems(@RequestParam(defaultValue = "0") Integer pageNo,
                                            @RequestParam(defaultValue = "10") Integer pageSize,
                                            @RequestParam(defaultValue = "id") String sortBy){
        return new ResponseEntity(itemService.getItems(pageNo, pageSize, sortBy), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ItemDto> createItem(@Valid @RequestBody ItemCreateRequest itemCreateRequest){
        return new ResponseEntity(itemService.createItem(itemCreateRequest), HttpStatus.CREATED);
    }

}