package com.finartz.restaurantapp.model.converter.dtoconverter;

import com.finartz.restaurantapp.exception.EntityNotFoundException;
import com.finartz.restaurantapp.model.dto.ItemDto;
import com.finartz.restaurantapp.model.entity.ItemEntity;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ItemDtoConverterTest {

    @Spy
    private ItemDtoConverter itemDtoConverter;

    @Test
    public void whenPassValidItemEntity_thenReturnItemDto(){
        ItemEntity itemEntity = ItemEntity.builder()
                .id(1l)
                .name("Item")
                .build();

        ItemDto itemDto = itemDtoConverter.convert(itemEntity);

        Assertions.assertEquals(itemDto.getName(), itemEntity.getName());

    }

    @Test(expected = EntityNotFoundException.class)
    public void whenPassNullItemEntity_thenReturnThrowEntityNotFoundException(){
        itemDtoConverter.convert(null);
    }

}
