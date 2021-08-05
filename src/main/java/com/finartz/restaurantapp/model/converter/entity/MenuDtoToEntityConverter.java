package com.finartz.restaurantapp.model.converter.entity;

import com.finartz.restaurantapp.model.converter.GenericConverter;
import com.finartz.restaurantapp.model.dto.BranchDto;
import com.finartz.restaurantapp.model.dto.MenuDto;
import com.finartz.restaurantapp.model.entity.BranchEntity;
import com.finartz.restaurantapp.model.entity.MenuEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MenuDtoToEntityConverter implements GenericConverter<MenuDto, MenuEntity> {

    private final GenericConverter<BranchDto, BranchEntity> branchEntityConverter;
//    private final GenericConverter<MealDto, MealEntity> mealEntityConverter;

    @Override
    public MenuEntity convert(final MenuDto menu){
        if(menu == null){
            return null;
        }

        MenuEntity menuEntity = new MenuEntity();

        menuEntity.setId(menu.getId());
        menuEntity.setBranchEntity(convert(menu.getBranch()));

//        List<MealEntity> mealEntities = new ArrayList<>();
//        menu.getMeals().forEach(meal -> {
//            mealEntities.add(convert(meal));
//        });
//        menuEntity.setMealEntities(mealEntities);

        return menuEntity;
    }

    private BranchEntity convert(BranchDto branch){
        return branchEntityConverter.convert(branch);
    }

//    private MealEntity convert(MealDto meal){
//        return mealEntityConverter.convert(meal);
//    }

}