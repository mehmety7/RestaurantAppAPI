package com.finartz.restaurantapp.controller;

import com.finartz.restaurantapp.model.dto.BranchDto;
import com.finartz.restaurantapp.model.request.create.BranchCreateRequest;
import com.finartz.restaurantapp.service.BranchService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("branch")
@RequiredArgsConstructor
public class BranchController {

    private final BranchService branchService;

    @GetMapping("{id}")
    public ResponseEntity<BranchDto> getBranch(@PathVariable Long id){
        return new ResponseEntity(branchService.getBranch(id), HttpStatus.OK);
    }

    @GetMapping("county")
    public ResponseEntity<List<BranchDto>> getBranches(@RequestParam Long countyId){
        return new ResponseEntity(branchService.getBranches(countyId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<BranchDto> createBranch(@Valid @RequestBody BranchCreateRequest branchCreateRequest, @RequestHeader("Authorization") String jwt){
        return new ResponseEntity(branchService.createBranch(branchCreateRequest, jwt), HttpStatus.CREATED);
    }

}
