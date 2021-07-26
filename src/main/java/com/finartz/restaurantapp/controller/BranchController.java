package com.finartz.restaurantapp.controller;

import com.finartz.restaurantapp.model.Branch;
import com.finartz.restaurantapp.model.enumerated.Status;
import com.finartz.restaurantapp.service.BranchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("branch")
public class BranchController {

    @Autowired
    private BranchService branchService;

    @PostMapping
    public ResponseEntity<Branch> create(@RequestBody Branch branch){
        return new ResponseEntity(branchService.create(branch), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Branch>> getAll(){
        return new ResponseEntity(branchService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<Branch>> getById(@PathVariable Long id){
        return new ResponseEntity(branchService.getById(id), HttpStatus.OK);
    }

    @GetMapping("/waiting")
    public ResponseEntity<List<Branch>> getWaiting(){
        return new ResponseEntity(branchService.getByStatus(Status.WAITING), HttpStatus.OK);
    }

    @GetMapping("/bycounty")
    public ResponseEntity<List<Branch>> getByCounty(Long county_id){
        return new ResponseEntity(branchService.getByCounty(county_id), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Branch> update(@RequestBody Branch branch){
        return new ResponseEntity(branchService.update(branch), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Branch> deleteById(@PathVariable Long id){
        branchService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
