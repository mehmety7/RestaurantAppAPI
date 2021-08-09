package com.finartz.restaurantapp.model.converter.entity.fromCreateRequest;

import com.finartz.restaurantapp.model.converter.GenericConverter;
import com.finartz.restaurantapp.model.dto.BranchDto;
import com.finartz.restaurantapp.model.dto.MealDto;
import com.finartz.restaurantapp.model.entity.BranchEntity;
import com.finartz.restaurantapp.model.entity.MealEntity;
import com.finartz.restaurantapp.model.entity.MenuEntity;
import com.finartz.restaurantapp.model.request.create.MenuCreateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class MenuCreateRequestToEntityConverter implements GenericConverter<MenuCreateRequest, MenuEntity> {

    private final GenericConverter<BranchDto, BranchEntity> branchEntityConverter;
    private final GenericConverter<MealDto, MealEntity> mealEntityConverter;

    @Override
    public MenuEntity convert(final MenuCreateRequest menuCreateRequest){
        if(menuCreateRequest == null){
            return null;
        }

        MenuEntity menuEntity = new MenuEntity();

        menuEntity.setBranchEntity(convert(menuCreateRequest.getBranch()));

        List<MealEntity> mealEntities = new ArrayList<>();
        menuCreateRequest.getMeals().forEach(meal -> {
            mealEntities.add(convert(meal));
        });
        menuEntity.setMealEntities(mealEntities);

        return menuEntity;
    }

    private BranchEntity convert(BranchDto branch){
        return branchEntityConverter.convert(branch);
    }

    private MealEntity convert(MealDto meal){
        return mealEntityConverter.convert(meal);
    }

}