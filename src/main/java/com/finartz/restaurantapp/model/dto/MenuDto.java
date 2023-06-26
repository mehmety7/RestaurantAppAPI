package com.finartz.restaurantapp.model.dto;

import lombok.*;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MenuDto extends BaseDto {

    private Long id;

    private Long branchId;

    private List<MealDto> meals;

}
