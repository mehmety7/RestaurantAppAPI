package com.finartz.restaurantapp.model.request.create;

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

    @NotNull(message = "Restaurant user may not be null")
    private Long userId;

}
