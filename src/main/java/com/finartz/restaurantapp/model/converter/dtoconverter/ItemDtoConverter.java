package com.finartz.restaurantapp.model.converter.dtoconverter;

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
        if(Objects.isNull(itemEntity)){
            return null;
        }

        ItemDto itemDto = new ItemDto();

        if(Objects.nonNull(itemEntity.getId())){
            itemDto.setId(itemEntity.getId());
        }
        if(Objects.nonNull(itemDto.getName())){
            itemDto.setName(itemEntity.getName());
        }
        if(Objects.nonNull(itemEntity.getUnitType())){
            itemDto.setUnitType(itemEntity.getUnitType());
        }

        return itemDto;
    }

}
