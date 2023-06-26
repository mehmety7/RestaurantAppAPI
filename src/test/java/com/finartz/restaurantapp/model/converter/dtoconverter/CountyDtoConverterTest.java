package com.finartz.restaurantapp.model.converter.dtoconverter;

import com.finartz.restaurantapp.exception.EntityNotFoundException;
import com.finartz.restaurantapp.model.dto.CountyDto;
import com.finartz.restaurantapp.model.entity.CityEntity;
import com.finartz.restaurantapp.model.entity.CountyEntity;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CountyDtoConverterTest {

    @Spy
    private CountyDtoConverter countyDtoConverter;

    @Test
    public void whenPassValidCountyEntity_thenReturnCountyDto(){
        CountyEntity countyEntity = CountyEntity.builder()
                .id(1L)
                .name("County")
                .cityEntity(CityEntity.builder().id(1L).build())
                .build();

        CountyDto countyDto = countyDtoConverter.convert(countyEntity);

        Assertions.assertEquals(countyDto.getName(), countyEntity.getName());

    }

    @Test(expected = EntityNotFoundException.class)
    public void whenPassNullCountyEntity_thenReturnThrowEntityNotFoundException(){
        countyDtoConverter.convert(null);
    }

}