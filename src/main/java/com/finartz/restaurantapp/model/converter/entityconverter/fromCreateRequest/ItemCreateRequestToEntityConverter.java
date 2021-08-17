package com.finartz.restaurantapp.model.converter.entityconverter.fromCreateRequest;

import com.finartz.restaurantapp.model.converter.GenericConverter;
import com.finartz.restaurantapp.model.entity.ItemEntity;
import com.finartz.restaurantapp.model.request.create.ItemCreateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ItemCreateRequestToEntityConverter implements GenericConverter<ItemCreateRequest, ItemEntity> {

    @Override
    public ItemEntity convert(final ItemCreateRequest itemCreateRequest){
        if(itemCreateRequest == null){
            return null;
        }

        ItemEntity itemEntity = new ItemEntity();

        itemEntity.setName(itemCreateRequest.getName());
        itemEntity.setUnitType(itemCreateRequest.getUnitType());

        return itemEntity;
    }

}