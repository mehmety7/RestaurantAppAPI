package com.finartz.restaurantapp.service;

import com.finartz.restaurantapp.exception.EntityNotFoundException;
import com.finartz.restaurantapp.model.converter.dtoconverter.CityDtoConverter;
import com.finartz.restaurantapp.model.dto.CityDto;
import com.finartz.restaurantapp.model.entity.CityEntity;
import com.finartz.restaurantapp.repository.CityRepository;
import com.finartz.restaurantapp.service.impl.CityServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;

@RunWith(MockitoJUnitRunner.class)
public class CityServiceTest {

    private static final String CITY_ISTANBUL = "İstanbul";
    private static final String CITY_KIRKKALE = "Kırkkale";

    @InjectMocks
    private CityServiceImpl cityService;

    @Mock
    private CityRepository cityRepository;

    @Mock
    private CityDtoConverter cityDtoConverter;

    @Test
    public void whenFetchAll_thenReturnAllCity() {
        CityEntity cityEntity = CityEntity.builder().id(1L).name(CITY_ISTANBUL).build();
        CityDto city = CityDto.builder().id(1L).name(CITY_ISTANBUL).build();
        List<CityEntity> cityEntities = Collections.singletonList(cityEntity);
        List<CityDto> cities = Collections.singletonList(city);


        Mockito.when(cityDtoConverter.convert(cityEntity)).thenReturn(city);
        Mockito.when(cityRepository.findAll()).thenReturn(cityEntities);

        List<CityDto> resultCities = cityService.getCities();

        assertEquals(resultCities.size(), cities.size());
    }

    @Test
    public void whenFetchByValidId_thenReturnCity() {
        CityEntity cityEntity = CityEntity.builder().name(CITY_ISTANBUL).build();

        CityDto city = CityDto.builder().name(CITY_ISTANBUL).build();

        Mockito.when(cityDtoConverter.convert(cityEntity)).thenReturn(city);
        Mockito.when(cityRepository.findById(1L)).thenReturn(Optional.ofNullable(cityEntity));

        CityDto resultCity = cityService.getCity(1L);

        assertEquals(city, resultCity);
    }

    @Test(expected = EntityNotFoundException.class)
    public void whenFetchByInvalidId_thenThrowEntityNotFoundException() {

        Mockito.when(cityRepository.findById(anyLong())).thenReturn(Optional.empty());
        cityService.getCity(anyLong());

    }

}
