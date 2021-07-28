package com.finartz.restaurantapp.service;

import com.finartz.restaurantapp.model.Branch;
import com.finartz.restaurantapp.model.enumerated.Status;
import com.finartz.restaurantapp.repository.BranchRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BranchService {

    private final BranchRepository branchRepository;

    public BranchService(BranchRepository branchRepository) {
        this.branchRepository = branchRepository;
    }


    public Branch create(Branch branch) {
        return branchRepository.save(branch);
    }

    public List<Branch> getAll() {
        return branchRepository.findAll();
    }

    public Branch getById(Long id) {
        return branchRepository.getById(id);
    }

    public List<Branch> getByStatus(Status status) {
        return branchRepository.findByStatus(status);
    }

    public List<Branch> getByCounty(Long county_id) {
        return branchRepository.findByAddress_County_Id(county_id);
    }

    public Branch update(Branch branch) {
        Branch update = branchRepository.getById(branch.getId());
        if (update != null) {
            branchRepository.save(branch);
            return update;
        }
        return branch;
    }

    public Branch deleteById(Long id) {
        Branch branch = branchRepository.getById(id);
        if (branch != null) {
            branchRepository.deleteById(id);
            return branch;
        }
        return branch;
    }
}
