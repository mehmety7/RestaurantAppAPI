package com.finartz.restaurantapp.model.request;

import com.finartz.restaurantapp.model.dto.ItemDto;
import com.finartz.restaurantapp.model.dto.MenuDto;
import lombok.Data;

import java.util.List;

@Data
public class MealRequest {

    private Long id;

    private String name;

    private Double price;

    private MenuDto menu;

    private List<ItemDto> items;

}
