package com.finartz.restaurantapp.controller;

import com.finartz.restaurantapp.model.dto.MenuDto;
import com.finartz.restaurantapp.model.request.create.MenuCreateRequest;
import com.finartz.restaurantapp.service.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("menu")
@RequiredArgsConstructor
public class MenuController {

    private final MenuService menuService;

    @GetMapping("{id}")
    public ResponseEntity<MenuDto> getMenu(@PathVariable Long id){
        return new ResponseEntity<>(menuService.getMenu(id), HttpStatus.OK);
    }

    @GetMapping("branch")
    public ResponseEntity<MenuDto> getBranchMenu(@RequestParam("branch_id") Long branchId){
        return new ResponseEntity<>(menuService.getBranchMenu(branchId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<MenuDto> createMenu(@Valid @RequestBody MenuCreateRequest menuCreateRequest){
        return new ResponseEntity<>(menuService.createMenu(menuCreateRequest), HttpStatus.CREATED);
    }

}