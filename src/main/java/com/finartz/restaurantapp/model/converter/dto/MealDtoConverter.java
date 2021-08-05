package com.finartz.restaurantapp.model.converter.dto;

import com.finartz.restaurantapp.model.converter.GenericConverter;
import com.finartz.restaurantapp.model.dto.ItemDto;
import com.finartz.restaurantapp.model.dto.MealDto;
import com.finartz.restaurantapp.model.dto.MenuDto;
import com.finartz.restaurantapp.model.entity.ItemEntity;
import com.finartz.restaurantapp.model.entity.MealEntity;
import com.finartz.restaurantapp.model.entity.MenuEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class MealDtoConverter implements GenericConverter<MealEntity, MealDto> {

    private final GenericConverter<MenuEntity, MenuDto> menuDtoConverter;
    private final GenericConverter<ItemEntity, ItemDto> itemDtoConverter;

    @Override
    public MealDto convert(final MealEntity mealEntity){
        if(mealEntity == null){
            return null;
        }

        MealDto mealDto = new MealDto();

        mealDto.setId(mealEntity.getId());
        mealDto.setName(mealEntity.getName());
        mealDto.setPrice(mealEntity.getPrice());
        mealDto.setMenu(convert(mealEntity.getMenuEntity()));

        List<ItemDto> items = new ArrayList<>();
        mealEntity.getItemEntities().forEach(itemEntity -> {
            items.add(convert(itemEntity));
        });
        mealDto.setItems(items);

        return mealDto;
    }

    private MenuDto convert(final MenuEntity menuEntity){
        return menuDtoConverter.convert(menuEntity);
    }

    private ItemDto convert(final ItemEntity itemEntity){
        return itemDtoConverter.convert(itemEntity);
    }

}
