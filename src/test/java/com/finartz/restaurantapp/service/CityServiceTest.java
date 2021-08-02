package com.finartz.restaurantapp.service;

import com.finartz.restaurantapp.model.City;
import com.finartz.restaurantapp.repository.CityRepository;
import com.finartz.restaurantapp.service.impl.CityServiceImpl;
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
public class CityServiceTest {

    private static final String CITY_ISTANBUL = "İstanbul";
    private static final String CITY_KIRKKALE = "Kırkkale";

    @InjectMocks
    private CityServiceImpl cityService;

    @Mock
    private CityRepository cityRepository;


    @Test
    public void whenFetchAll_thenReturnAllCity() {
        City city1 = City.builder().id(1l).name(CITY_ISTANBUL).build();
        City city2 = City.builder().id(2l).name(CITY_KIRKKALE).build();
        List<City> cityList = Arrays.asList(city1, city2);

        Mockito.when(cityRepository.findAll()).thenReturn(cityList);

        List<City> fetchedList = cityService.getAll();

        assertEquals(cityList.size(), fetchedList.size());
    }

    @Test
    public void whenFetchById_thenReturnCity() {
        City city = City.builder().name(CITY_ISTANBUL).build();

        Mockito.when(cityRepository.getById(1L)).thenReturn(city);

        City fetchedCity = cityService.getById(1L);

        assertEquals(city.getId(), fetchedCity.getId());
    }

    @Test
    public void whenAddCity_thenReturnSavedCity() {
        City city = City.builder().name(CITY_ISTANBUL).build();

        Mockito.doReturn(city).when(cityRepository).save(city);

        City returnedCity = cityService.create(city);

        assertEquals(city.getName(), returnedCity.getName());
    }

    @Test
    public void whenUpdateCity_thenReturnUpdatedCity(){
        City foundCity = City.builder().id(1l).name(CITY_ISTANBUL).build();
        City modifyCity = City.builder().id(1l).name(CITY_KIRKKALE).build();

        Mockito.when(cityRepository.getById(1l)).thenReturn(foundCity);
        Mockito.when(cityRepository.save(modifyCity)).thenReturn(modifyCity);

        City updatedCity = cityService.update(modifyCity);

        Assertions.assertNotEquals(updatedCity.getName(), CITY_ISTANBUL);
        Assertions.assertEquals(updatedCity.getName(), CITY_KIRKKALE);

    }

}
