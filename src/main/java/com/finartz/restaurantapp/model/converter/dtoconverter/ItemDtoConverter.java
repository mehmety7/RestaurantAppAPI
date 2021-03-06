package com.finartz.restaurantapp.model.converter.dtoconverter;

import com.finartz.restaurantapp.exception.EntityNotFoundException;
import com.finartz.restaurantapp.model.converter.GenericConverter;
import com.finartz.restaurantapp.model.dto.ItemDto;
import com.finartz.restaurantapp.model.entity.ItemEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@RequiredArgsConstructor
public class ItemDtoConverter implements GenericConverter<ItemEntity, ItemDto> {

    @Override
    public ItemDto convert(final ItemEntity itemEntity){
        if (Objects.isNull(itemEntity)){
            throw new EntityNotFoundException("Not found item entity");
        }

        ItemDto itemDto = new ItemDto();

        itemDto.setId(itemEntity.getId());
        itemDto.setName(itemEntity.getName());
        itemDto.setUnitType(itemEntity.getUnitType());

        return itemDto;
    }

}
