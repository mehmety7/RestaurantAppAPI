package com.finartz.restaurantapp.repository;

import com.finartz.restaurantapp.model.Branch;
import com.finartz.restaurantapp.model.enumerated.Status;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BranchRepository extends BaseRepository<Branch> {

    List<Branch> findByStatus(Status status);

    List<Branch> findByAddress_County_Id(Long county_id);

}
