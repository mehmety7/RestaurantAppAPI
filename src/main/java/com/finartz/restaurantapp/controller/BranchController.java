package com.finartz.restaurantapp.controller;

import com.finartz.restaurantapp.model.entity.BranchEntity;
import com.finartz.restaurantapp.model.request.create.BranchCreateRequest;
import com.finartz.restaurantapp.service.BranchService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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

    @GetMapping("county")
    public ResponseEntity<List<BranchEntity>> getBranches(@RequestParam Long countyId){
        return new ResponseEntity(branchService.getBranches(countyId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<BranchEntity> createBranch(@Valid @RequestBody BranchCreateRequest branchCreateRequest){
        return new ResponseEntity(branchService.createBranch(branchCreateRequest), HttpStatus.CREATED);
    }

}
