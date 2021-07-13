package com.finartz.restaurantapp.controller;

import com.finartz.restaurantapp.model.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/menu/")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @PostMapping("create")
    public ResponseEntity<Menu> create(@RequestBody Menu menu){
        return new ResponseEntity<>(menuService.create(menu), HttpStatus.CREATED);
    }

    @PutMapping("update")
    public ResponseEntity<Menu> update(@RequestBody Menu menu){
        return new ResponseEntity<>(menuService.update(menu), HttpStatus.OK);
    }

    @GetMapping("get/{id}")
    public ResponseEntity<Menu> get(@PathVariable Long id){
        return new ResponseEntity<>(menuService.getById(id), HttpStatus.OK);
    }

    @GetMapping("getAll")
    public ResponseEntity<List<Menu>> getAll(){
        return new ResponseEntity<>(menuService.getAll(), HttpStatus.OK)
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id){
        menuService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
