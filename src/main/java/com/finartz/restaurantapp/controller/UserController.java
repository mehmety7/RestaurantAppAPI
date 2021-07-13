package com.finartz.restaurantapp.controller;

import com.finartz.restaurantapp.model.Branch;
import com.finartz.restaurantapp.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user/")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("create")
    public ResponseEntity<User> create(@RequestBody User user){
        return new ResponseEntity<>(userService.create(user), HttpStatus.CREATED);
    }

    @PutMapping("update")
    public ResponseEntity<User> update(@RequestBody User user){
        return new ResponseEntity<>(userService.update(user), HttpStatus.OK);
    }

    @GetMapping("get/{id}")
    public ResponseEntity<User> get(@PathVariable Long id){
        return new ResponseEntity<>(userService.getById(id), HttpStatus.OK);
    }

    @GetMapping("getAll")
    public ResponseEntity<List<User>> getAll(){
        return new ResponseEntity<>(userService.getAll(), HttpStatus.OK)
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id){
        userService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("getWaitingRestaurants")
    public ResponseEntity<List<Branch>> getWaitingRestaurants(){
        return new ResponseEntity(userService.getWaitingRestaurants(), HttpStatus.OK);
    }

    @GetMapping("getWaitingBranchs")
    public ResponseEntity<List<Branch>> getWaitingBranchs(){
        return new ResponseEntity(userService.getWaitingBranchs(), HttpStatus.OK);
    }

}
