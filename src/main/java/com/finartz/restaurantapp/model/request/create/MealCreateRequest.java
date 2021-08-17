package com.finartz.restaurantapp.model.request.create;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MealCreateRequest {

    @NotNull(message = "Meal name may not be null")
    private String name;

    @NotNull(message = "Meal price may not be null")
    private Double price;

    @NotNull(message = "Meal menu may not be null")
    private Long menuId;

    @NotNull(message = "Meal items may not be null")
    private List<Long> itemIds;

}
