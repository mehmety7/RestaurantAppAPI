package com.finartz.restaurantapp.model.dto;

import com.finartz.restaurantapp.model.enumerated.RestaurantStatus;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RestaurantDto extends BaseDto {

    private Long id;

    private String name;

    private RestaurantStatus restaurantStatus;

    private Long userId;

}
