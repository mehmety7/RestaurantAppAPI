package com.finartz.restaurantapp.controller;

import com.finartz.restaurantapp.model.Branch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("branch")
public class BranchController {

    @Autowired
    private BranchService branchService;

    @PostMapping
    public ResponseEntity<Branch> create(@RequestBody Branch branch){
        return new ResponseEntity(branchService.create(branch), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Branch>> getBranches(){
        return new ResponseEntity(branchService.getBranchRequest(), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Branch> update(@RequestBody Branch branch){
        return new ResponseEntity(branchService.update(branch), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Branch> deleteById(@PathVariable Long id){
        branchService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
