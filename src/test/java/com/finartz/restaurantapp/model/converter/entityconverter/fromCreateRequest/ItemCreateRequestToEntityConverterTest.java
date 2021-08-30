package com.finartz.restaurantapp.model.converter.entityconverter.fromCreateRequest;

import com.finartz.restaurantapp.exception.EntityNotFoundException;
import com.finartz.restaurantapp.model.entity.ItemEntity;
import com.finartz.restaurantapp.model.request.create.ItemCreateRequest;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ItemCreateRequestToEntityConverterTest {

    @Spy
    private ItemCreateRequestToEntityConverter itemCreateRequestToEntityConverter;

    @Test
    public void whenPassValidItemCreateRequest_thenReturnItemEntity(){
        ItemCreateRequest itemCreateRequest = ItemCreateRequest.builder()
                .name("Item")
                .build();

        ItemEntity itemEntity = itemCreateRequestToEntityConverter.convert(itemCreateRequest);

        Assertions.assertEquals(itemEntity.getName(), itemCreateRequest.getName());
    }

    @Test(expected = EntityNotFoundException.class)
    public void whenPassNullItemCreateRequest_thenThrowEntityNotFoundException(){
        itemCreateRequestToEntityConverter.convert(null);
    }

}