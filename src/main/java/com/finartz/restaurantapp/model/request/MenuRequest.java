package com.finartz.restaurantapp.model.request;

import com.finartz.restaurantapp.model.dto.BranchDto;
import com.finartz.restaurantapp.model.dto.MealDto;
import lombok.Data;

import java.util.List;

@Data
public class MenuRequest {

    private Long id;

    private BranchDto branch;

    private List<MealDto> meals;

}
