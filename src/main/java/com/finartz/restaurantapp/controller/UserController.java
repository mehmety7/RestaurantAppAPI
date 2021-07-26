package com.finartz.restaurantapp.controller;

import com.finartz.restaurantapp.model.User;
import com.finartz.restaurantapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<User> create(@RequestBody User user){
        return new ResponseEntity(userService.create(user), HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<User> get(@PathVariable Long id){
        return new ResponseEntity(userService.getById(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<User>> getAll(){
        return new ResponseEntity(userService.getAll(), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<User> update(@RequestBody User user){
        return new ResponseEntity(userService.update(user), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<User> deleteById(@PathVariable Long id){
        userService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
