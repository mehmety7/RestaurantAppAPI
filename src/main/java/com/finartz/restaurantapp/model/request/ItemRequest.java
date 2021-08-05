package com.finartz.restaurantapp.model.request;

import com.finartz.restaurantapp.model.dto.MealDto;
import lombok.Data;

import java.util.List;

@Data
public class ItemRequest {

    private Long id;

    private String name;

    private String unitType;

    private List<MealDto> meals;

}
