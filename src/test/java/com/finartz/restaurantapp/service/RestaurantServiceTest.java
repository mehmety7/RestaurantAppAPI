package com.finartz.restaurantapp.service;

import com.finartz.restaurantapp.model.Restaurant;
import com.finartz.restaurantapp.model.enumerated.Status;
import com.finartz.restaurantapp.repository.RestaurantRepository;
import com.finartz.restaurantapp.service.impl.RestaurantServiceImpl;
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
public class RestaurantServiceTest {

    private static final String NAME_KRAL_BURGER = "Kral Burger";
    private static final String NAME_LEZZET_EVI = "Lezzet Evi";

    @InjectMocks
    private RestaurantServiceImpl restaurantService;

    @Mock
    private RestaurantRepository restaurantRepository;


    @Test
    public void whenFetchAll_thenReturnAllRestaurant() {
        Restaurant restaurant1 = Restaurant.builder().id(1l).name(NAME_KRAL_BURGER).build();
        Restaurant restaurant2 = Restaurant.builder().id(2l).name(NAME_LEZZET_EVI).build();
        List<Restaurant> restaurantList = Arrays.asList(restaurant1, restaurant2);

        Mockito.when(restaurantRepository.findAll()).thenReturn(restaurantList);

        List<Restaurant> fetchedList = restaurantService.getAll();

        assertEquals(restaurantList.size(), fetchedList.size());
    }

    @Test
    public void whenFetchById_thenReturnRestaurant() {
        Restaurant restaurant = Restaurant.builder().name(NAME_KRAL_BURGER).build();

        Mockito.when(restaurantRepository.getById(1L)).thenReturn(restaurant);

        Restaurant fetchedRestaurant = restaurantService.getById(1L);

        assertEquals(restaurant.getId(), fetchedRestaurant.getId());
    }

    @Test
    public void whenAddRestaurant_thenReturnSavedRestaurant() {
        Restaurant restaurant = Restaurant.builder().name(NAME_KRAL_BURGER).build();

        Mockito.doReturn(restaurant).when(restaurantRepository).save(restaurant);

        Restaurant returnedRestaurant = restaurantService.create(restaurant);

        assertEquals(restaurant.getName(), returnedRestaurant.getName());
    }

    @Test
    public void whenFetchedByStatus_thenReturnSomeRestaurants() {
        Restaurant restaurant1 = Restaurant.builder().status(Status.WAITING).build();
        Restaurant restaurant2 = Restaurant.builder().status(Status.WAITING).build();
        Restaurant restaurant3 = Restaurant.builder().status(Status.WAITING).build();
        List<Restaurant> restaurantList = Arrays.asList(restaurant1,restaurant2,restaurant3);

        Mockito.when(restaurantRepository.findByStatus(Status.WAITING)).thenReturn(restaurantList);

        List<Restaurant> fetchedList = restaurantService.findByStatus(Status.WAITING);

        assertEquals(restaurantList, fetchedList);
    }
    @Test
    public void whenUpdateRestaurant_thenReturnUpdatedRestaurant(){
        Restaurant foundRestaurant = Restaurant.builder().id(1l).name(NAME_KRAL_BURGER).build();
        Restaurant modifyRestaurant = Restaurant.builder().id(1l).name(NAME_LEZZET_EVI).build();

        Mockito.when(restaurantRepository.getById(1l)).thenReturn(foundRestaurant);
        Mockito.when(restaurantRepository.save(modifyRestaurant)).thenReturn(modifyRestaurant);

        Restaurant updatedRestaurant = restaurantService.update(modifyRestaurant);

        Assertions.assertNotEquals(updatedRestaurant.getName(), NAME_KRAL_BURGER);
        Assertions.assertEquals(updatedRestaurant.getName(), NAME_LEZZET_EVI);

    }


}