package com.finartz.restaurantapp.model.converter.entityconverter.fromCreateRequest;

import com.finartz.restaurantapp.model.converter.GenericConverter;
import com.finartz.restaurantapp.model.entity.ItemEntity;
import com.finartz.restaurantapp.model.request.create.ItemCreateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@RequiredArgsConstructor
public class ItemCreateRequestToEntityConverter implements GenericConverter<ItemCreateRequest, ItemEntity> {

    @Override
    public ItemEntity convert(final ItemCreateRequest itemCreateRequest){
        if(Objects.isNull(itemCreateRequest)){
            return null;
        }

        ItemEntity itemEntity = new ItemEntity();

        if (Objects.nonNull(itemCreateRequest.getName())){
            itemEntity.setName(itemCreateRequest.getName());
        }
        if (Objects.nonNull(itemCreateRequest.getUnitType())) {
            itemEntity.setUnitType(itemCreateRequest.getUnitType());
        }

        return itemEntity;
    }

}