package com.finartz.restaurantapp.model.converter.dtoconverter;

import com.finartz.restaurantapp.model.dto.CountyDto;
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
                .id(1l)
                .name("County")
                .build();

        CountyDto countyDto = countyDtoConverter.convert(countyEntity);

        Assertions.assertEquals(countyDto.getName(), countyEntity.getName());

    }

}