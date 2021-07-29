package com.finartz.restaurantapp.service;

import com.finartz.restaurantapp.model.County;
import com.finartz.restaurantapp.repository.CountyRepository;
import org.junit.Test;
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

    @InjectMocks
    private CountyService countyService;

    @Mock
    private CountyRepository countyRepository;


    @Test
    public void whenFetchAll_thenReturnAllCounty() {
        County county1 = County.builder().id(1l).name("Ümraniye").build();
        County county2 = County.builder().id(2l).name("Avcılar").build();
        List<County> countyList = Arrays.asList(county1, county2);

        Mockito.when(countyRepository.findAll()).thenReturn(countyList);

        List<County> fetchedList = countyService.getAll();

        assertEquals(countyList.size(), fetchedList.size());
    }

    @Test
    public void whenFetchById_thenReturnCounty() {
        County county = County.builder().name("Ümraniye").build();

        Mockito.when(countyRepository.getById(1L)).thenReturn(county);

        County fetchedCounty = countyService.getById(1L);

        assertEquals(county.getId(), fetchedCounty.getId());
    }

    @Test
    public void whenAddCounty_thenReturnSavedCounty() {
        County county = County.builder().name("Ümraniye").build();

        Mockito.doReturn(county).when(countyRepository).save(county);

        County returnedCounty = countyService.create(county);

        assertEquals(county.getName(), returnedCounty.getName());
    }

    @Test
    public void whenUpdateCounty_thenReturnUpdatedCounty(){
        County county = County.builder().name("Ümraniye").build();

        Mockito.when(countyRepository.getById(1L)).thenReturn(county);
        Mockito.when(countyRepository.save(county)).thenReturn(county);

        County updatedCounty = countyService.update(county);

        assertEquals(county , updatedCounty);

    }

}
