package com.finartz.restaurantapp.service;

import com.finartz.restaurantapp.model.County;
import com.finartz.restaurantapp.repository.CountyRepository;
import com.finartz.restaurantapp.service.impl.CountyServiceImpl;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class CountyServiceTest {

    private static final String COUNTY_UMRANIYE = "Ümraniye";
    private static final String COUNTY_AVCILAR = "Avcılar";

    @InjectMocks
    private CountyServiceImpl countyService;

    @Mock
    private CountyRepository countyRepository;


    @Test
    public void whenFetchAll_thenReturnAllCounty() {
        County county1 = County.builder().id(1l).name(COUNTY_UMRANIYE).build();
        County county2 = County.builder().id(2l).name(COUNTY_AVCILAR).build();
        List<County> countyList = Arrays.asList(county1, county2);

        Mockito.when(countyRepository.findAll()).thenReturn(countyList);

        List<County> fetchedList = countyService.getAll();

        assertEquals(countyList.size(), fetchedList.size());
    }

    @Test
    public void whenFetchById_thenReturnCounty() {
        County county = County.builder().name(COUNTY_UMRANIYE).build();

        Mockito.when(countyRepository.getById(1L)).thenReturn(county);

        County fetchedCounty = countyService.getById(1L);

        assertEquals(county.getId(), fetchedCounty.getId());
    }

    @Test
    public void whenAddCounty_thenReturnSavedCounty() {
        County county = County.builder().name(COUNTY_UMRANIYE).build();

        Mockito.doReturn(county).when(countyRepository).save(county);

        County returnedCounty = countyService.create(county);

        assertEquals(county.getName(), returnedCounty.getName());
    }

    @Test
    public void whenUpdateCounty_thenReturnUpdatedCounty(){
        County foundCounty = County.builder().id(1l).name(COUNTY_UMRANIYE).build();
        County modifyCounty = County.builder().id(1l).name(COUNTY_AVCILAR).build();

        Mockito.when(countyRepository.getById(1l)).thenReturn(foundCounty);
        Mockito.when(countyRepository.save(modifyCounty)).thenReturn(modifyCounty);

        County updatedCounty = countyService.update(modifyCounty);

        Assertions.assertNotEquals(updatedCounty.getName(), COUNTY_UMRANIYE);
        Assertions.assertEquals(updatedCounty.getName(), COUNTY_AVCILAR);

    }

}
