package com.finartz.restaurantapp.model.dto;

import lombok.*;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MealDto extends BaseDto {

    private Long id;

    private String name;

    private Double price;

    private List<ItemDto> items;

}
