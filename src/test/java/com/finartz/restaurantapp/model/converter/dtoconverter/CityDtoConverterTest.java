package com.finartz.restaurantapp.model.converter.dtoconverter;

import com.finartz.restaurantapp.model.dto.CityDto;
import com.finartz.restaurantapp.model.dto.CountyDto;
import com.finartz.restaurantapp.model.entity.CityEntity;
import com.finartz.restaurantapp.model.entity.CountyEntity;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CityDtoConverterTest {

    @Spy
    @InjectMocks
    private CityDtoConverter cityDtoConverter;

    @Mock
    private CountyDtoConverter countyDtoConverter;

    @Test
    public void whenPassValidCityEntity_thenReturnCityDto(){
        CityEntity cityEntity = CityEntity.builder()
                .id(1l)
                .name("City")
                .build();

        CountyEntity countyEntity = CountyEntity.builder().build();
        CountyDto countyDto = CountyDto.builder().build();

        Mockito.when(countyDtoConverter.convert(countyEntity)).thenReturn(countyDto);
        CityDto cityDto = cityDtoConverter.convert(cityEntity);

        Assertions.assertEquals(cityDto.getName(), cityEntity.getName());

    }

}
