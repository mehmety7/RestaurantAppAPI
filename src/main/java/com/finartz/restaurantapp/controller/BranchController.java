package com.finartz.restaurantapp.controller;

import com.finartz.restaurantapp.model.entity.BranchEntity;
import com.finartz.restaurantapp.model.enumerated.Status;
import com.finartz.restaurantapp.model.request.create.BranchCreateRequest;
import com.finartz.restaurantapp.model.request.update.BranchUpdateRequest;
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

    @GetMapping("{id}")
    public ResponseEntity<BranchEntity> getBranch(@PathVariable Long id){
        return new ResponseEntity(branchService.getBranch(id), HttpStatus.OK);
    }

    @GetMapping("waiting")
    public ResponseEntity<List<BranchEntity>> getBranches(Status status){
        return new ResponseEntity(branchService.getBranches(status), HttpStatus.OK);
    }

    @GetMapping("by-county")
    public ResponseEntity<List<BranchEntity>> getBranches(Long countyId){
        return new ResponseEntity(branchService.getBranches(countyId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<BranchEntity> createBranch(@RequestBody BranchCreateRequest branchCreateRequest){
        return new ResponseEntity(branchService.createBranch(branchCreateRequest), HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<BranchEntity> updateBranch(@PathVariable Long id, @RequestBody BranchUpdateRequest branchUpdateRequest){
        return new ResponseEntity(branchService.updateBranch(id, branchUpdateRequest), HttpStatus.OK);
    }

}
