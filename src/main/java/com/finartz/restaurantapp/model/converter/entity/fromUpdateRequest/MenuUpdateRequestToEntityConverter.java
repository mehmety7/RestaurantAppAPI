package com.finartz.restaurantapp.model.converter.entity.fromUpdateRequest;

import com.finartz.restaurantapp.model.converter.entity.fromDto.MealDtoToEntityConverter;
import com.finartz.restaurantapp.model.dto.MealDto;
import com.finartz.restaurantapp.model.entity.MealEntity;
import com.finartz.restaurantapp.model.entity.MenuEntity;
import com.finartz.restaurantapp.model.request.update.MenuUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class MenuUpdateRequestToEntityConverter{

    private final MealDtoToEntityConverter mealDtoToEntityConverter;

    public MenuEntity convert(final MenuUpdateRequest menuUpdateRequest,
                              final MenuEntity menuExisted){
        if (menuUpdateRequest == null)
            return null;

        if(menuUpdateRequest.getMeals() != null) {
            List<MealEntity> mealEntities = new ArrayList<>();
            menuUpdateRequest.getMeals().forEach(meal -> {
                mealEntities.add(convert(meal));
            });
            menuExisted.setMealEntities(mealEntities);
        }

        return menuExisted;
    }

    private MealEntity convert(final MealDto meal){
        return mealDtoToEntityConverter.convert(meal);
    }

}
