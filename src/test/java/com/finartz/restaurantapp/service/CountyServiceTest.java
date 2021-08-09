package com.finartz.restaurantapp.service;

import com.finartz.restaurantapp.model.converter.dto.CountyDtoConverter;
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

import static org.junit.jupiter.api.Assertions.assertEquals;

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
        Mockito.when(countyRepository.getById(1L)).thenReturn(countyEntity);

        CountyDto resultCounty = countyService.getCounty(1L);

        assertEquals(county, resultCounty);
    }

}
