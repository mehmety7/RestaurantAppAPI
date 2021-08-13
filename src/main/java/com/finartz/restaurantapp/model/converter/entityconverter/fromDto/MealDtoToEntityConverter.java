package com.finartz.restaurantapp.model.converter.entityconverter.fromDto;

import com.finartz.restaurantapp.model.converter.GenericConverter;
import com.finartz.restaurantapp.model.dto.ItemDto;
import com.finartz.restaurantapp.model.dto.MealDto;
import com.finartz.restaurantapp.model.entity.ItemEntity;
import com.finartz.restaurantapp.model.entity.MealEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class MealDtoToEntityConverter implements GenericConverter<MealDto, MealEntity> {

//    private final GenericConverter<MenuDto, MenuEntity> menuEntityConverter;
    private final GenericConverter<ItemDto, ItemEntity> itemEntityConverter;

    @Override
    public MealEntity convert(final MealDto meal){
        if(meal == null){
            return null;
        }

        MealEntity mealEntity = new MealEntity();

        mealEntity.setId(meal.getId());
        mealEntity.setName(meal.getName());
        mealEntity.setPrice(meal.getPrice());
//        mealEntity.setMenuEntity(convert(meal.getMenu()));

        List<ItemEntity> itemEntities = new ArrayList<>();
        meal.getItems().forEach(item -> {
            itemEntities.add(convert(item));
        });
        mealEntity.setItemEntities(itemEntities);

        return mealEntity;
    }

//    private MenuEntity convert(final MenuDto menu){
//        return menuEntityConverter.convert(menu);
//    }

    private ItemEntity convert(final ItemDto item){
        return itemEntityConverter.convert(item);
    }

}