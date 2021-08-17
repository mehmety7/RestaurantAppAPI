package com.finartz.restaurantapp.model.converter.dtoconverter;

import com.finartz.restaurantapp.model.converter.GenericConverter;
import com.finartz.restaurantapp.model.dto.MealDto;
import com.finartz.restaurantapp.model.dto.MenuDto;
import com.finartz.restaurantapp.model.entity.MealEntity;
import com.finartz.restaurantapp.model.entity.MenuEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class MenuDtoConverter implements GenericConverter<MenuEntity, MenuDto> {

    private final GenericConverter<MealEntity, MealDto> mealDtoConverter;

    @Override
    public MenuDto convert(final MenuEntity menuEntity){
        if(menuEntity == null){
            return null;
        }

        MenuDto menuDto = new MenuDto();

        menuDto.setId(menuEntity.getId());

        List<MealDto> meals = new ArrayList<>();
        menuEntity.getMealEntities().forEach(mealEntity -> {
            meals.add(convert(mealEntity));
        });
        menuDto.setMeals(meals);

        return menuDto;
    }

    private MealDto convert(final MealEntity mealEntity){
        return mealDtoConverter.convert(mealEntity);
    }

}
