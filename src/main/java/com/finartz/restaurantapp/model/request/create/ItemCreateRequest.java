package com.finartz.restaurantapp.model.request.create;

import com.finartz.restaurantapp.model.dto.MealDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ItemCreateRequest {

    private String name;

    private String unitType;

    private List<MealDto> meals;

}
