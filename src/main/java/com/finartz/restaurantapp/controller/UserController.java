package com.finartz.restaurantapp.controller;

import com.finartz.restaurantapp.model.entity.UserEntity;
import com.finartz.restaurantapp.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<UserEntity>> getUsers(){
        return new ResponseEntity(userService.getUsers(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<UserEntity> getUser(@PathVariable Long id){
        return new ResponseEntity(userService.getUser(id), HttpStatus.OK);
    }

    @GetMapping("email/{email}")
    public ResponseEntity getUser(@PathVariable String email) {
        return new ResponseEntity(userService.getUser(email), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<UserEntity> createUser(@RequestBody UserEntity userEntity){
        return new ResponseEntity(userService.createUser(userEntity), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<UserEntity> updateUser(@RequestBody UserEntity userEntity){
        return new ResponseEntity(userService.updateUser(userEntity), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<UserEntity> deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
