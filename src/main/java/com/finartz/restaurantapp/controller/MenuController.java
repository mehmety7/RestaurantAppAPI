package com.finartz.restaurantapp.controller;

import com.finartz.restaurantapp.model.dto.MenuDto;
import com.finartz.restaurantapp.model.request.create.MenuCreateRequest;
import com.finartz.restaurantapp.service.MenuService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("menu")
public class MenuController {

    private final MenuService menuService;

    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }

    @GetMapping("{id}")
    public ResponseEntity<MenuDto> getMenu(@PathVariable Long id){
        return new ResponseEntity(menuService.getMenu(id), HttpStatus.OK);
    }

    @GetMapping("branch")
    public ResponseEntity<MenuDto> getBranchMenu(@RequestParam Long branch_id){
        return new ResponseEntity(menuService.getBranchMenu(branch_id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<MenuDto> createMenu(@Valid @RequestBody MenuCreateRequest menuCreateRequest){
        return new ResponseEntity(menuService.createMenu(menuCreateRequest), HttpStatus.CREATED);
    }

}