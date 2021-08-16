package com.finartz.restaurantapp.model.request.create;

import com.finartz.restaurantapp.model.dto.ItemDto;
import com.finartz.restaurantapp.model.dto.MenuDto;
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
    private MenuDto menu;

    @NotNull(message = "Meal items may not be null")
    private List<ItemDto> items;

}
