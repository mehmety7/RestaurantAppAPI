package com.finartz.restaurantapp.model.converter.dtoconverter;

import com.finartz.restaurantapp.exception.EntityNotFoundException;
import com.finartz.restaurantapp.model.converter.GenericConverter;
import com.finartz.restaurantapp.model.dto.ItemDto;
import com.finartz.restaurantapp.model.dto.MealDto;
import com.finartz.restaurantapp.model.entity.ItemEntity;
import com.finartz.restaurantapp.model.entity.MealEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class MealDtoConverter implements GenericConverter<MealEntity, MealDto> {

    private final GenericConverter<ItemEntity, ItemDto> itemDtoConverter;

    @Override
    public MealDto convert(final MealEntity mealEntity){
        if (Objects.isNull(mealEntity)){
            throw new EntityNotFoundException("Not found meal entity");
        }

        MealDto mealDto = new MealDto();

        mealDto.setId(mealEntity.getId());
        mealDto.setName(mealEntity.getName());
        mealDto.setPrice(mealEntity.getPrice());

        List<ItemDto> items = new ArrayList<>();
        if(Objects.nonNull(mealEntity.getItemEntities())){
            mealEntity.getItemEntities().forEach(itemEntity -> items.add(convert(itemEntity)));
        }
        mealDto.setItems(items);

        return mealDto;
    }

    private ItemDto convert(final ItemEntity itemEntity){
        return itemDtoConverter.convert(itemEntity);
    }

}
