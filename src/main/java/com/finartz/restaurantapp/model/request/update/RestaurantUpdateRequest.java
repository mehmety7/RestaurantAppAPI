package com.finartz.restaurantapp.model.request.update;

import com.finartz.restaurantapp.model.enumerated.RestaurantStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantUpdateRequest {

    @NotNull(message = "Restaurant id may not be null for updating")
    private Long id;

    private RestaurantStatus restaurantStatus;

}