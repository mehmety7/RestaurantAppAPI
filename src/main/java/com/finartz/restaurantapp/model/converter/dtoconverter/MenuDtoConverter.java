package com.finartz.restaurantapp.model.converter.dtoconverter;

import com.finartz.restaurantapp.exception.EntityNotFoundException;
import com.finartz.restaurantapp.model.converter.GenericConverter;
import com.finartz.restaurantapp.model.dto.MealDto;
import com.finartz.restaurantapp.model.dto.MenuDto;
import com.finartz.restaurantapp.model.entity.MealEntity;
import com.finartz.restaurantapp.model.entity.MenuEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class MenuDtoConverter implements GenericConverter<MenuEntity, MenuDto> {

    private final GenericConverter<MealEntity, MealDto> mealDtoConverter;

    @Override
    public MenuDto convert(final MenuEntity menuEntity){
        if (Objects.isNull(menuEntity)){
            throw new EntityNotFoundException("Not found menu entity");
        }

        MenuDto menuDto = new MenuDto();

        menuDto.setId(menuEntity.getId());

        if(Objects.nonNull(menuEntity.getBranchEntity())){
            menuDto.setBranchId(menuEntity.getBranchEntity().getId());
        }

        List<MealDto> meals = new ArrayList<>();
        if(Objects.nonNull(menuEntity.getMealEntities())){
            menuEntity.getMealEntities().forEach(mealEntity -> meals.add(convert(mealEntity)));
        }
        menuDto.setMeals(meals);

        return menuDto;
    }

    private MealDto convert(final MealEntity mealEntity){
        return mealDtoConverter.convert(mealEntity);
    }

}
