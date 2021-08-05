package com.finartz.restaurantapp.model.converter.dto;

import com.finartz.restaurantapp.model.converter.GenericConverter;
import com.finartz.restaurantapp.model.dto.BranchDto;
import com.finartz.restaurantapp.model.dto.MenuDto;
import com.finartz.restaurantapp.model.entity.BranchEntity;
import com.finartz.restaurantapp.model.entity.MenuEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MenuDtoConverter implements GenericConverter<MenuEntity, MenuDto> {

    private final GenericConverter<BranchEntity, BranchDto> branchDtoConverter;
//    private final GenericConverter<MealEntity, MealDto> mealDtoConverter;

    @Override
    public MenuDto convert(final MenuEntity menuEntity){
        if(menuEntity == null){
            return null;
        }

        MenuDto menuDto = new MenuDto();

        menuDto.setId(menuEntity.getId());
        menuDto.setBranch(convert(menuEntity.getBranchEntity()));

//        List<MealDto> meals = new ArrayList<>();
//        menuEntity.getMealEntities().forEach(mealEntity -> {
//            meals.add(convert(mealEntity));
//        });
//        menuDto.setMeals(meals);

        return menuDto;
    }

    private BranchDto convert(final BranchEntity branchEntity){
        return  branchDtoConverter.convert(branchEntity);
    }

//    private MealDto convert(final MealEntity mealEntity){
//        return mealDtoConverter.convert(mealEntity);
//    }

}
