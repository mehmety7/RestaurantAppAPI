package com.finartz.restaurantapp.model.request;

import com.finartz.restaurantapp.model.dto.AddressDto;
import com.finartz.restaurantapp.model.dto.MenuDto;
import com.finartz.restaurantapp.model.dto.RestaurantDto;
import com.finartz.restaurantapp.model.enumerated.Status;
import lombok.Data;

@Data
public class BranchRequest {

    private Long id;

    private String name;

    private Status status;

    private RestaurantDto restaurant;

    private MenuDto menu;

    private AddressDto address;

}
