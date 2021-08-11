package com.finartz.restaurantapp.model.converter.entityconverter.fromCreateRequest;

import com.finartz.restaurantapp.model.converter.GenericConverter;
import com.finartz.restaurantapp.model.dto.ItemDto;
import com.finartz.restaurantapp.model.dto.MenuDto;
import com.finartz.restaurantapp.model.entity.ItemEntity;
import com.finartz.restaurantapp.model.entity.MealEntity;
import com.finartz.restaurantapp.model.entity.MenuEntity;
import com.finartz.restaurantapp.model.request.create.MealCreateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class MealCreateRequestToEntityConverter implements GenericConverter<MealCreateRequest, MealEntity> {

    private final GenericConverter<MenuDto, MenuEntity> menuEntityConverter;
    private final GenericConverter<ItemDto, ItemEntity> itemEntityConverter;

    @Override
    public MealEntity convert(final MealCreateRequest mealCreateRequest){
        if(mealCreateRequest == null){
            return null;
        }

        MealEntity mealEntity = new MealEntity();

        mealEntity.setName(mealCreateRequest.getName());
        mealEntity.setPrice(mealCreateRequest.getPrice());
        mealEntity.setMenuEntity(convert(mealCreateRequest.getMenu()));

        List<ItemEntity> itemEntities = new ArrayList<>();
        mealCreateRequest.getItems().forEach(item -> {
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