package com.finartz.restaurantapp.model.converter.entity;

import com.finartz.restaurantapp.model.converter.GenericConverter;
import com.finartz.restaurantapp.model.dto.MealDto;
import com.finartz.restaurantapp.model.entity.ItemEntity;
import com.finartz.restaurantapp.model.entity.MealEntity;
import com.finartz.restaurantapp.model.request.ItemRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ItemRequestToEntityConverter implements GenericConverter<ItemRequest, ItemEntity> {

    private final GenericConverter<MealDto, MealEntity> mealEntityConverter;

    @Override
    public ItemEntity convert(final ItemRequest itemRequest){
        if(itemRequest == null){
            return null;
        }

        ItemEntity itemEntity = new ItemEntity();

        itemEntity.setId(itemRequest.getId());
        itemEntity.setName(itemRequest.getName());
        itemEntity.setUnitType(itemRequest.getUnitType());

        List<MealEntity> mealEntities = new ArrayList<>();
        itemRequest.getMeals().forEach(meal -> {
            mealEntities.add(convert(meal));
        });
        itemEntity.setMealEntities(mealEntities);

        return itemEntity;
    }

    private MealEntity convert(final MealDto meal){
        return mealEntityConverter.convert(meal);
    }

}