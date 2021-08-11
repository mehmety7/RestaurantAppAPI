package com.finartz.restaurantapp.model.dto;

import com.finartz.restaurantapp.model.enumerated.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BranchDto {

    private Long id;

    private String name;

    private Status status;

    private RestaurantDto restaurant;


//    private MenuDto menu;
//    private AddressDto address;
}
