package com.finartz.restaurantapp.model.converter.dto;

import com.finartz.restaurantapp.model.converter.GenericConverter;
import com.finartz.restaurantapp.model.dto.ItemDto;
import com.finartz.restaurantapp.model.entity.ItemEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ItemDtoConverter implements GenericConverter<ItemEntity, ItemDto> {

//    private final GenericConverter<MealEntity, MealDto> mealDtoConverter;

    @Override
    public ItemDto convert(final ItemEntity itemEntity){
        if(itemEntity == null){
            return null;
        }

        ItemDto itemDto = new ItemDto();

        itemDto.setId(itemEntity.getId());
        itemDto.setName(itemEntity.getName());
        itemDto.setUnitType(itemEntity.getUnitType());

//        List<MealDto> meals = new ArrayList<>();
//        itemEntity.getMealEntities().forEach(mealEntity -> {
//            meals.add(convert(mealEntity));
//        });
//        itemDto.setMeals(meals);

        return itemDto;
    }

//    private MealDto convert(final MealEntity mealEntity){
//        return mealDtoConverter.convert(mealEntity);
//    }

}
