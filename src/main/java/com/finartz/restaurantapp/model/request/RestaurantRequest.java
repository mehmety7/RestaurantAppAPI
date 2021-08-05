package com.finartz.restaurantapp.model.request;

import com.finartz.restaurantapp.model.dto.BranchDto;
import com.finartz.restaurantapp.model.dto.UserDto;
import com.finartz.restaurantapp.model.enumerated.Status;
import lombok.Data;

import java.util.List;

@Data
public class RestaurantRequest {

    private Long id;

    private String name;

    private Status status;

    private UserDto user;

    private List<BranchDto> branches;

}
