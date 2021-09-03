package com.finartz.restaurantapp.model.dto;

import com.finartz.restaurantapp.model.enumerated.RestaurantStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RestaurantDto {

    private Long id;

    private String name;

    private RestaurantStatus restaurantStatus;

    private Long userId;

}
