package com.finartz.restaurantapp.controller;

import com.finartz.restaurantapp.model.dto.BranchDto;
import com.finartz.restaurantapp.model.dto.PageDto;
import com.finartz.restaurantapp.model.request.create.BranchCreateRequest;
import com.finartz.restaurantapp.model.request.get.BranchPageGetRequest;
import com.finartz.restaurantapp.service.BranchService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("branch")
@RequiredArgsConstructor
public class BranchController {

    private final BranchService branchService;

    @GetMapping("{id}")
    public ResponseEntity<BranchDto> getBranch(@PathVariable Long id){
        return new ResponseEntity<>(branchService.getBranch(id), HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<PageDto<BranchDto>> getBranches(@Valid @RequestBody BranchPageGetRequest branchPageGetRequest){
        return new ResponseEntity<>(branchService.getBranches(branchPageGetRequest), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<BranchDto> createBranch(@Valid @RequestBody BranchCreateRequest branchCreateRequest){
        return new ResponseEntity<>(branchService.createBranch(branchCreateRequest), HttpStatus.CREATED);
    }

}
