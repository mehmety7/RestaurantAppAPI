package com.finartz.restaurantapp.model.request.create;

import com.finartz.restaurantapp.model.dto.ItemDto;
import com.finartz.restaurantapp.model.dto.MenuDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MealCreateRequest {

    private String name;

    private Double price;

    private MenuDto menu;

    private List<ItemDto> items;

}
