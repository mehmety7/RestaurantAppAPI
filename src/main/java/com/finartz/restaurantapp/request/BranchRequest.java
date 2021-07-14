package com.finartz.restaurantapp.request;

import com.finartz.restaurantapp.model.enumerated.Status;
import lombok.Data;

@Data
public class BranchRequest {

    private Long id;

    private Long county_id;

    private Status status = Status.WAITING;

}
