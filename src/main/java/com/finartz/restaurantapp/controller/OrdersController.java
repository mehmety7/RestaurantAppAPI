package com.finartz.restaurantapp.controller;

import com.finartz.restaurantapp.model.Orders;
import com.finartz.restaurantapp.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("orders")
public class OrdersController {

    @Autowired
    private OrdersService ordersService;

    @PostMapping
    public ResponseEntity<Orders> create(@RequestBody Orders orders){
        return new ResponseEntity(ordersService.create(orders), HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<Orders> get(@PathVariable Long id){
        return new ResponseEntity(ordersService.getById(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Orders>> getAll(){
        return new ResponseEntity(ordersService.getAll(), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Orders> update(@RequestBody Orders orders){
        return new ResponseEntity(ordersService.update(orders), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Orders> deleteById(@PathVariable Long id){
        ordersService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
