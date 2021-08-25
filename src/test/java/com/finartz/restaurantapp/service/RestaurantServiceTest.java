package com.finartz.restaurantapp.service;

import com.finartz.restaurantapp.model.converter.dtoconverter.RestaurantDtoConverter;
import com.finartz.restaurantapp.model.converter.entityconverter.fromCreateRequest.RestaurantCreateRequestToEntityConverter;
import com.finartz.restaurantapp.model.converter.entityconverter.fromUpdateRequest.RestaurantUpdateRequestToEntityConverter;
import com.finartz.restaurantapp.model.dto.RestaurantDto;
import com.finartz.restaurantapp.model.entity.RestaurantEntity;
import com.finartz.restaurantapp.model.enumerated.Status;
import com.finartz.restaurantapp.model.request.create.RestaurantCreateRequest;
import com.finartz.restaurantapp.model.request.update.RestaurantUpdateRequest;
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
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class RestaurantServiceTest {

    private static final String NAME_KRAL_BURGER = "Kral Burger";

    @InjectMocks
    private RestaurantServiceImpl restaurantService;

    @Mock
    private RestaurantRepository restaurantRepository;

    @Mock
    private RestaurantDtoConverter restaurantDtoConverter;

    @Mock
    private RestaurantCreateRequestToEntityConverter restaurantCreateRequestToEntityConverter;

    @Mock
    private RestaurantUpdateRequestToEntityConverter restaurantUpdateRequestToEntityConverter;

    @Mock
    private TokenService tokenService;

    @Test
    public void whenFetchById_thenReturnRestaurant() {
        RestaurantEntity restaurantEntity = RestaurantEntity.builder().name(NAME_KRAL_BURGER).build();
        RestaurantDto restaurant = RestaurantDto.builder().name(NAME_KRAL_BURGER).build();

        Mockito.when(restaurantDtoConverter.convert(restaurantEntity)).thenReturn(restaurant);
        Mockito.when(restaurantRepository.findById(1L)).thenReturn(Optional.ofNullable(restaurantEntity));

        RestaurantDto resultRestaurant = restaurantService.getRestaurant(1L);

        assertEquals(restaurant.getId(), resultRestaurant.getId());
    }


    @Test
    public void whenFetchedByStatus_thenReturnSomeRestaurants() {
        RestaurantEntity restaurantEntity = RestaurantEntity.builder().status(Status.WAITING).build();
        RestaurantDto restaurant = RestaurantDto.builder().status(Status.WAITING).build();

        List<RestaurantEntity> restaurantEntities = Arrays.asList(restaurantEntity);
        List<RestaurantDto> restaurants = Arrays.asList(restaurant);

        Mockito.when(restaurantRepository.findByStatus(Status.WAITING)).thenReturn(restaurantEntities);
        Mockito.when(restaurantDtoConverter.convert(restaurantEntity)).thenReturn(restaurant);

        List<RestaurantDto> resultRestaurants = restaurantService.getRestaurants(Status.WAITING);

        assertEquals(restaurants, resultRestaurants);
    }

    @Test
    public void whenAddRestaurant_thenReturnSavedRestaurant() {
        RestaurantEntity restaurantEntity = RestaurantEntity.builder().name(NAME_KRAL_BURGER).build();
        RestaurantDto restaurant = RestaurantDto.builder().name(NAME_KRAL_BURGER).build();
        RestaurantCreateRequest restaurantCreateRequest = RestaurantCreateRequest.builder().name(NAME_KRAL_BURGER).build();

        Mockito.when(restaurantCreateRequestToEntityConverter.convert(restaurantCreateRequest)).thenReturn(restaurantEntity);
        Mockito.when(restaurantRepository.save(restaurantEntity)).thenReturn(restaurantEntity);
        Mockito.when(restaurantDtoConverter.convert(restaurantEntity)).thenReturn(restaurant);

        RestaurantDto resultRestaurant = restaurantService.createRestaurant(restaurantCreateRequest);

        assertEquals(restaurant.getName(), resultRestaurant.getName());
    }

    @Test
    public void whenUpdateRestaurant_thenReturnUpdatedRestaurant(){
        RestaurantEntity restaurantEntity = RestaurantEntity.builder().id(1l).status(Status.WAITING).build();
        RestaurantEntity restaurantEntityUpdated = RestaurantEntity.builder().id(1l).status(Status.CANCELED).build();
        RestaurantDto restaurant = RestaurantDto.builder().id(1l).status(Status.WAITING).build();
        RestaurantDto restaurantUpdated = RestaurantDto.builder().id(1l).status(Status.CANCELED).build();
        RestaurantUpdateRequest restaurantUpdateRequest = RestaurantUpdateRequest.builder().build();

        Mockito.when(tokenService.isRequestOwnerAuthoritative(restaurant.getUserId())).thenReturn(true);
        Mockito.when(restaurantRepository.getById(1l)).thenReturn(restaurantEntity);
        Mockito.when(restaurantUpdateRequestToEntityConverter.convert(restaurantUpdateRequest, restaurantEntity)).thenReturn(restaurantEntityUpdated);
        Mockito.when(restaurantRepository.save(restaurantEntityUpdated)).thenReturn(restaurantEntityUpdated);
        Mockito.when(restaurantDtoConverter.convert(restaurantEntityUpdated)).thenReturn(restaurantUpdated);

        RestaurantDto resultRestaurant = restaurantService.updateRestaurant(1L, restaurantUpdateRequest);

        Assertions.assertNotEquals(restaurant.getStatus(), Status.CANCELED);
        Assertions.assertEquals(resultRestaurant.getStatus(), Status.CANCELED);

    }

    @Test
    public void whenIsRestaurantStatusApproved_thenReturnTrue(){
        RestaurantEntity restaurantEntity = RestaurantEntity.builder()
                .id(1l).status(Status.APPROVED).build();

        Mockito.when(restaurantRepository.getById(1l)).thenReturn(restaurantEntity);

        Boolean result = restaurantService.isRestaurantApproved(1l);

        Assertions.assertEquals(result, true);
    }

}