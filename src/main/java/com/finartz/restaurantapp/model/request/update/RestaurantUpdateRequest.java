package com.finartz.restaurantapp.model.request.update;

import com.finartz.restaurantapp.model.enumerated.RestaurantStatus;
import com.finartz.restaurantapp.model.request.BaseRequest;
import lombok.*;

import javax.validation.constraints.NotNull;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantUpdateRequest extends BaseRequest {

    @NotNull(message = "Restaurant id may not be null for updating")
    private Long id;

    private RestaurantStatus restaurantStatus;

}