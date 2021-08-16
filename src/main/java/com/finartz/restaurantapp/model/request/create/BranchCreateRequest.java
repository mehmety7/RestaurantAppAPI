package com.finartz.restaurantapp.model.request.create;

import com.finartz.restaurantapp.model.dto.AddressDto;
import com.finartz.restaurantapp.model.dto.MenuDto;
import com.finartz.restaurantapp.model.dto.RestaurantDto;
import com.finartz.restaurantapp.model.enumerated.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BranchCreateRequest {

    @NotNull(message = "Branch name may not be null")
    private String name;

    private Status status = Status.WAITING;

    @NotNull(message = "Branch city may not be null")
    private RestaurantDto restaurant;

    private MenuDto menu;

    private AddressDto address;

}
