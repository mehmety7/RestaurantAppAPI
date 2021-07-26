package com.finartz.restaurantapp.controller;

import com.finartz.restaurantapp.model.Menu;
import com.finartz.restaurantapp.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("menu")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @PostMapping
    public ResponseEntity<Menu> create(@RequestBody Menu menu){
        return new ResponseEntity(menuService.create(menu), HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<Menu> get(@PathVariable Long id){
        return new ResponseEntity(menuService.getById(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Menu>> getAll(){
        return new ResponseEntity(menuService.getAll(), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Menu> update(@RequestBody Menu menu){
        return new ResponseEntity(menuService.update(menu), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Menu> deleteById(@PathVariable Long id){
        menuService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}