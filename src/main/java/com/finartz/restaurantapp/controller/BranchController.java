package com.finartz.restaurantapp.controller;

import com.finartz.restaurantapp.model.entity.AddressEntity;
import com.finartz.restaurantapp.model.entity.BranchEntity;
import com.finartz.restaurantapp.model.enumerated.Status;
import com.finartz.restaurantapp.service.BranchService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("branch")
public class BranchController {

    private final BranchService branchService;

    public BranchController(BranchService branchService) {
        this.branchService = branchService;
    }

    @GetMapping
    public ResponseEntity<List<BranchEntity>> getBranches(){
        return new ResponseEntity(branchService.getBranches(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<BranchEntity> getBranch(@PathVariable Long id){
        return new ResponseEntity(branchService.getBranch(id), HttpStatus.OK);
    }

    @GetMapping("waiting")
    public ResponseEntity<List<BranchEntity>> getBranches(Status status){
        return new ResponseEntity(branchService.getBranches(status), HttpStatus.OK);
    }

    @GetMapping("bycounty")
    public ResponseEntity<List<BranchEntity>> getBranches(AddressEntity addressEntity){
        return new ResponseEntity(branchService.getBranches(addressEntity), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<BranchEntity> createBranch(@RequestBody BranchEntity branchEntity){
        return new ResponseEntity(branchService.createBranch(branchEntity), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<BranchEntity> updateBranch(@RequestBody BranchEntity branchEntity){
        return new ResponseEntity(branchService.updateBranch(branchEntity), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<BranchEntity> deleteBranch(@PathVariable Long id){
        branchService.deleteBranch(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
