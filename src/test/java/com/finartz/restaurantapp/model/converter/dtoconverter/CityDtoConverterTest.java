package com.finartz.restaurantapp.model.converter.dtoconverter;

import com.finartz.restaurantapp.exception.EntityNotFoundException;
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

import java.util.Arrays;

@RunWith(MockitoJUnitRunner.class)
public class CityDtoConverterTest {

    @Spy
    @InjectMocks
    private CityDtoConverter cityDtoConverter;

    @Mock
    private CountyDtoConverter countyDtoConverter;

    @Test
    public void whenPassValidCityEntity_thenReturnCityDto(){
        CountyEntity countyEntity = CountyEntity.builder().id(1l).build();
        CountyDto countyDto = CountyDto.builder().build();

        CityEntity cityEntity = CityEntity.builder()
                .id(1l)
                .name("City")
                .countyEntities(Arrays.asList(countyEntity))
                .build();

        Mockito.when(countyDtoConverter.convert(countyEntity)).thenReturn(countyDto);
        CityDto cityDto = cityDtoConverter.convert(cityEntity);

        Assertions.assertEquals(cityDto.getName(), cityEntity.getName());

    }

    @Test(expected = EntityNotFoundException.class)
    public void whenPassNullCityEntity_thenReturnThrowEntityNotFoundException(){
        cityDtoConverter.convert(null);
    }

}
