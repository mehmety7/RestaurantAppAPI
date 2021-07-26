package com.finartz.restaurantapp.service;

import com.finartz.restaurantapp.model.Branch;
import com.finartz.restaurantapp.model.enumerated.Status;
import com.finartz.restaurantapp.repository.BranchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BranchService {

    @Autowired
    private BranchRepository branchRepository;

    public Branch create(Branch branch){
        Branch save = branchRepository.save(branch);
        return save;
    }

    public List<Branch> getAll(){
        List<Branch> branches = branchRepository.findAll();
        return branches;
    }

    public Branch getById(Long id){
        Branch branch = branchRepository.getById(id);
        return branch;
    }

    public List<Branch> getByStatus(Status status){
        List<Branch> branches = branchRepository.findByStatus(status);
        return branches;
    }

    public List<Branch> getByCounty(Long county_id){
        List<Branch> branches = branchRepository.findByCountyId(county_id);
        return branches;
    }

    public Branch update(Branch branch){
        Branch update = branchRepository.getById(branch.getId());
        if(update != null) {
            branchRepository.save(branch);
            return update;
        }
        return branch;
    }

    public Branch deleteById(Long id){
        Branch branch = branchRepository.getById(id);
        if (branch != null) {
            branchRepository.deleteById(id);
            return branch;
        }
        return branch;
    }
}
