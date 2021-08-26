package com.finartz.restaurantapp.service;

import com.finartz.restaurantapp.exception.EntityNotFoundException;
import com.finartz.restaurantapp.model.converter.dtoconverter.CountyDtoConverter;
import com.finartz.restaurantapp.model.dto.CountyDto;
import com.finartz.restaurantapp.model.entity.CountyEntity;
import com.finartz.restaurantapp.repository.CountyRepository;
import com.finartz.restaurantapp.service.impl.CountyServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;

@RunWith(MockitoJUnitRunner.class)
public class CountyServiceTest {

    private static final String COUNTY_UMRANIYE = "Ümraniye";
    private static final String COUNTY_AVCILAR = "Avcılar";

    @InjectMocks
    private CountyServiceImpl countyService;

    @Mock
    private CountyRepository countyRepository;

    @Mock
    private CountyDtoConverter countyDtoConverter;

    @Test
    public void whenFetchById_thenReturnCounty() {
        CountyEntity countyEntity = CountyEntity.builder().name(COUNTY_UMRANIYE).build();
        CountyDto county = CountyDto.builder().name(COUNTY_UMRANIYE).build();

        Mockito.when(countyDtoConverter.convert(countyEntity)).thenReturn(county);
        Mockito.when(countyRepository.findById(1L)).thenReturn(Optional.ofNullable(countyEntity));

        CountyDto resultCounty = countyService.getCounty(1L);

        assertEquals(county, resultCounty);
    }

    @Test(expected = EntityNotFoundException.class)
    public void whenFetchByInvalidId_thenThrowEntityNotFoundException() {

        Mockito.when(countyRepository.findById(anyLong())).thenReturn(Optional.empty());
        countyService.getCounty(anyLong());

    }

    @Test
    public void whenFetchByCityId_thenReturnCounties() {
        CountyEntity countyEntity = CountyEntity.builder().name(COUNTY_UMRANIYE).build();
        CountyDto county = CountyDto.builder().name(COUNTY_UMRANIYE).build();

        Mockito.when(countyDtoConverter.convert(countyEntity)).thenReturn(county);
        Mockito.when(countyRepository.getCountyEntitiesByCityEntity_Id(1L)).thenReturn(Arrays.asList(countyEntity));

        List<CountyDto> resultCounties = countyService.getCounties(1L);

        assertEquals(Arrays.asList(county), resultCounties);
    }


}
