package com.finartz.restaurantapp.model.converter.dtoconverter;

import com.finartz.restaurantapp.exception.EntityNotFoundException;
import com.finartz.restaurantapp.model.dto.AddressDto;
import com.finartz.restaurantapp.model.entity.*;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class AddressDtoConverterTest {

    @Spy
    private AddressDtoConverter addressDtoConverter;

    @Test
    public void whenPassValidUserAddressEntity_thenReturnAddressDto(){
        AddressEntity addressEntity = AddressEntity.builder()
                .id(1L)
                .name("Address")
                .cityEntity(CityEntity.builder().id(1L).name("city").build())
                .countyEntity(CountyEntity.builder().id(1L).name("county").build())
                .userEntity(UserEntity.builder().id(1L).build())
                .branchEntity(null)
                .build();

        AddressDto addressDto = addressDtoConverter.convert(addressEntity);
        Assertions.assertEquals(addressDto.getName(), addressEntity.getName());
    }

    @Test
    public void whenPassValidBranchAddressEntity_thenReturnAddressDto(){
        AddressEntity addressEntity = AddressEntity.builder()
                .id(1L)
                .name("Address")
                .cityEntity(CityEntity.builder().id(1L).name("city").build())
                .countyEntity(CountyEntity.builder().id(1L).name("county").build())
                .userEntity(null)
                .branchEntity(BranchEntity.builder().id(1L).build())
                .build();

        AddressDto addressDto = addressDtoConverter.convert(addressEntity);
        Assertions.assertEquals(addressDto.getName(), addressEntity.getName());
    }

    @Test(expected = EntityNotFoundException.class)
    public void whenPassNullAddressEntity_thenThrowEntityNotFoundException(){

        addressDtoConverter.convert(null);

    }

}
