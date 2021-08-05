package com.finartz.restaurantapp.model.converter.entity;

import com.finartz.restaurantapp.model.converter.GenericConverter;
import com.finartz.restaurantapp.model.dto.ItemDto;
import com.finartz.restaurantapp.model.dto.MenuDto;
import com.finartz.restaurantapp.model.entity.ItemEntity;
import com.finartz.restaurantapp.model.entity.MealEntity;
import com.finartz.restaurantapp.model.entity.MenuEntity;
import com.finartz.restaurantapp.model.request.MealRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class MealRequestToEntityConverter implements GenericConverter<MealRequest, MealEntity> {

    private final GenericConverter<MenuDto, MenuEntity> menuEntityConverter;
    private final GenericConverter<ItemDto, ItemEntity> itemEntityConverter;

    @Override
    public MealEntity convert(final MealRequest mealRequest){
        if(mealRequest == null){
            return null;
        }

        MealEntity mealEntity = new MealEntity();

        mealEntity.setId(mealRequest.getId());
        mealEntity.setName(mealRequest.getName());
        mealEntity.setPrice(mealRequest.getPrice());
        mealEntity.setMenuEntity(convert(mealRequest.getMenu()));

        List<ItemEntity> itemEntities = new ArrayList<>();
        mealRequest.getItems().forEach(item -> {
            itemEntities.add(convert(item));
        });
        mealEntity.setItemEntities(itemEntities);

        return mealEntity;
    }

    private MenuEntity convert(final MenuDto menu){
        return menuEntityConverter.convert(menu);
    }

    private ItemEntity convert(final ItemDto item){
        return itemEntityConverter.convert(item);
    }

}