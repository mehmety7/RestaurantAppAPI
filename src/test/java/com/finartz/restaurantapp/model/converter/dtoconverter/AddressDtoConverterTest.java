package com.finartz.restaurantapp.model.converter.dtoconverter;

import com.finartz.restaurantapp.model.dto.AddressDto;
import com.finartz.restaurantapp.model.entity.AddressEntity;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class AddressDtoConverterTest {

    @Spy
    AddressDtoConverter addressDtoConverter;

    @Test
    public void whenPassValidAddressEntity_thenReturnAddressDto(){
        AddressEntity addressEntity = AddressEntity.builder()
                .id(1l)
                .name("Address")
                .build();

        AddressDto addressDto = addressDtoConverter.convert(addressEntity);
        Assertions.assertEquals(addressDto.getName(), addressEntity.getName());
    }

}
