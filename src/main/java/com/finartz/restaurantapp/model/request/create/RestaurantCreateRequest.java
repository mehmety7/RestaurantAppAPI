package com.finartz.restaurantapp.model.request.create;

import com.finartz.restaurantapp.model.dto.UserDto;
import com.finartz.restaurantapp.model.enumerated.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantCreateRequest {

    @NotNull(message = "Restaurant name may not be null")
    private String name;

    private Status status = Status.WAITING;

    @NotNull(message = "Restaurant user may not be null")
    private UserDto user;

}
