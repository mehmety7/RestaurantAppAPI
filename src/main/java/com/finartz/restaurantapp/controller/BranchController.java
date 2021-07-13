package com.finartz.restaurantapp.controller;

import com.finartz.restaurantapp.model.Address;
import com.finartz.restaurantapp.model.Branch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@RestController
@RequestMapping("/api/branch/")
public class BranchController {

    @Autowired
    private BranchService branchService;

    @PostMapping("create")
    public ResponseEntity<Branch> create(@RequestBody Branch branch){
        return new ResponseEntity<>(branchService.create(branch), HttpStatus.CREATED);
    }

    @PutMapping("update")
    public ResponseEntity<Branch> update(@RequestBody Branch branch){
        return new ResponseEntity<>(branchService.update(branch), HttpStatus.OK);
    }

    @GetMapping("get/{id}")
    public ResponseEntity<Branch> getById(@PathVariable Long id){
        try{
            return new ResponseEntity<>(branchService.getById(id), HttpStatus.OK);
        } catch (EntityNotFoundException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Branch Not Found", e);
        }
    }

    @GetMapping("getAll")
    public ResponseEntity<List<Branch>> getAll(){
        return new ResponseEntity(branchService.getAll(), HttpStatus.OK);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id){
        branchService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("getClosest")
    public ResponseEntity<List<Branch>> getClosestByCounty(@RequestParam Address address){
        return new ResponseEntity(branchService.getClosestByCounty(address), HttpStatus.OK);
    }

}
