package com.finartz.restaurantapp.service;

import com.finartz.restaurantapp.model.Branch;
import com.finartz.restaurantapp.model.enumerated.Status;

import java.util.List;

public interface BranchService {

    public Branch create(Branch branch);

    public List<Branch> getAll();

    public Branch getById(Long id);

    public List<Branch> findByStatus(Status status);

    public List<Branch> findByAddress_County_Id(Long county_id);

    public Branch update(Branch branch);

    public Branch deleteById(Long id);
}
