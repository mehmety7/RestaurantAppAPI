package com.finartz.restaurantapp.controller;

import com.finartz.restaurantapp.model.entity.MenuEntity;
import com.finartz.restaurantapp.service.MenuService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("menu")
public class MenuController {

    private final MenuService menuService;

    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }

    @GetMapping("{id}")
    public ResponseEntity<MenuEntity> getMenu(@PathVariable Long id){
        return new ResponseEntity(menuService.getMenu(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<MenuEntity>> getMenus(){
        return new ResponseEntity(menuService.getMenus(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<MenuEntity> createMenu(@RequestBody MenuEntity menuEntity){
        return new ResponseEntity(menuService.createMenu(menuEntity), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<MenuEntity> updateMenu(@RequestBody MenuEntity menuEntity){
        return new ResponseEntity(menuService.updateMenu(menuEntity), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<MenuEntity> deleteMenu(@PathVariable Long id){
        menuService.deleteMenu(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}