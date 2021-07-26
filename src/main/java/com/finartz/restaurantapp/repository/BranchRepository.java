package com.finartz.restaurantapp.repository;

import com.finartz.restaurantapp.model.Branch;
import com.finartz.restaurantapp.model.enumerated.Status;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BranchRepository extends BaseRepository<Branch> {

    @Query(value = " SELECT * FROM BRANCHES b WHERE b.status = 'WAITING' ", nativeQuery = true)
    List<Branch> findByStatus(Status status);

    @Query(value = " SELECT * FROM BRANCHES b WHERE b.county_id = ?1 ", nativeQuery = true)
    List<Branch> findByCountyId(Long county_id);

}
