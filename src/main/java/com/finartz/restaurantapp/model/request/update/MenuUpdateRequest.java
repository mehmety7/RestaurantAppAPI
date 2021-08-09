package com.finartz.restaurantapp.model.request.update;

import com.finartz.restaurantapp.model.dto.MealDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MenuUpdateRequest {

    private List<MealDto> meals;

}
