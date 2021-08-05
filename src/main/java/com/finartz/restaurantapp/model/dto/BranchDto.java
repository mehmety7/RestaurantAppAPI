package com.finartz.restaurantapp.model.dto;

import com.finartz.restaurantapp.model.enumerated.Status;
import lombok.Data;

@Data
public class BranchDto {

    private Long id;

    private String name;

    private Status status;

    private RestaurantDto restaurant;

}
