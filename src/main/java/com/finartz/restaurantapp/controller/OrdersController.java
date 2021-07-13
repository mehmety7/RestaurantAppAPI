package com.finartz.restaurantapp.controller;

import com.finartz.restaurantapp.model.Orders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders/")
public class OrdersController {

    @Autowired
    private OrdersService ordersService;

    @PostMapping("create")
    public ResponseEntity<Orders> create(@RequestBody Orders orders){
        return new ResponseEntity<>(ordersService.create(orders), HttpStatus.CREATED);
    }

    @PutMapping("update")
    public ResponseEntity<Orders> update(@RequestBody Orders orders){
        return new ResponseEntity<>(ordersService.update(orders), HttpStatus.OK);
    }

    @GetMapping("get/{id}")
    public ResponseEntity<Orders> get(@PathVariable Long id){
        return new ResponseEntity<>(ordersService.getById(id), HttpStatus.OK);
    }

    @GetMapping("getAll")
    public ResponseEntity<List<Orders>> getAll(){
        return new ResponseEntity<>(ordersService.getAll(), HttpStatus.OK)
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id){
        ordersService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
