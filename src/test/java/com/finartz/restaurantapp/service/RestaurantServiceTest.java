package com.finartz.restaurantapp.service;

import com.finartz.restaurantapp.exception.EntityNotFoundException;
import com.finartz.restaurantapp.model.converter.dtoconverter.RestaurantDtoConverter;
import com.finartz.restaurantapp.model.converter.entityconverter.fromcreaterequest.RestaurantCreateRequestToEntityConverter;
import com.finartz.restaurantapp.model.converter.entityconverter.fromupdaterequest.RestaurantUpdateRequestToEntityConverter;
import com.finartz.restaurantapp.model.dto.RestaurantDto;
import com.finartz.restaurantapp.model.entity.RestaurantEntity;
import com.finartz.restaurantapp.model.enumerated.RestaurantStatus;
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

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;

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
    public void whenFetchByValidId_thenReturnRestaurant() {
        RestaurantEntity restaurantEntity = RestaurantEntity.builder().name(NAME_KRAL_BURGER).build();
        RestaurantDto restaurant = RestaurantDto.builder().name(NAME_KRAL_BURGER).build();

        Mockito.when(restaurantDtoConverter.convert(restaurantEntity)).thenReturn(restaurant);
        Mockito.when(restaurantRepository.findById(1L)).thenReturn(Optional.ofNullable(restaurantEntity));

        RestaurantDto resultRestaurant = restaurantService.getRestaurant(1L);

        assertEquals(restaurant.getId(), resultRestaurant.getId());
    }

    @Test(expected = EntityNotFoundException.class)
    public void whenFetchByInvalidId_thenThrowEntityNotFoundException() {

        Mockito.when(restaurantRepository.findById(anyLong())).thenReturn(Optional.empty());
        restaurantService.getRestaurant(anyLong());

    }


    @Test
    public void whenFetchedByStatus_thenReturnSomeRestaurants() {
        RestaurantEntity restaurantEntity = RestaurantEntity.builder().restaurantStatus(RestaurantStatus.WAITING).build();
        RestaurantDto restaurant = RestaurantDto.builder().restaurantStatus(RestaurantStatus.WAITING).build();

        List<RestaurantEntity> restaurantEntities = Collections.singletonList(restaurantEntity);
        List<RestaurantDto> restaurants = Collections.singletonList(restaurant);

        Mockito.when(restaurantRepository.findByRestaurantStatus(RestaurantStatus.WAITING)).thenReturn(restaurantEntities);
        Mockito.when(restaurantDtoConverter.convert(restaurantEntity)).thenReturn(restaurant);

        List<RestaurantDto> resultRestaurants = restaurantService.getRestaurants(RestaurantStatus.WAITING);

        assertEquals(restaurants, resultRestaurants);
    }

    @Test
    public void whenAddRestaurant_thenReturnSavedRestaurant() {
        RestaurantEntity restaurantEntity = RestaurantEntity.builder().name(NAME_KRAL_BURGER).build();
        RestaurantDto restaurant = RestaurantDto.builder().name(NAME_KRAL_BURGER).build();
        RestaurantCreateRequest restaurantCreateRequest = RestaurantCreateRequest.builder().userId(1L).name(NAME_KRAL_BURGER).build();

        Mockito.doNothing().when(tokenService).checkRequestOwnerAuthoritative(restaurantCreateRequest.getUserId());
        Mockito.when(restaurantCreateRequestToEntityConverter.convert(restaurantCreateRequest)).thenReturn(restaurantEntity);
        Mockito.when(restaurantRepository.save(restaurantEntity)).thenReturn(restaurantEntity);
        Mockito.when(restaurantDtoConverter.convert(restaurantEntity)).thenReturn(restaurant);

        RestaurantDto resultRestaurant = restaurantService.createRestaurant(restaurantCreateRequest);

        assertEquals(restaurant.getName(), resultRestaurant.getName());
    }

    @Test
    public void giveValidId_whenUpdateRestaurant_thenReturnUpdatedRestaurant(){
        RestaurantEntity restaurantEntity = RestaurantEntity.builder().id(1L).restaurantStatus(RestaurantStatus.WAITING).build();
        RestaurantEntity restaurantEntityUpdated = RestaurantEntity.builder().id(1L).restaurantStatus(RestaurantStatus.CANCELED).build();
        RestaurantDto restaurant = RestaurantDto.builder().id(1L).restaurantStatus(RestaurantStatus.WAITING).build();
        RestaurantDto restaurantUpdated = RestaurantDto.builder().id(1L).restaurantStatus(RestaurantStatus.CANCELED).build();
        RestaurantUpdateRequest restaurantUpdateRequest = RestaurantUpdateRequest.builder().id(1L).build();

        Mockito.when(restaurantRepository.getById(restaurantUpdateRequest.getId())).thenReturn(restaurantEntity);
        Mockito.when(restaurantUpdateRequestToEntityConverter.convert(restaurantUpdateRequest, restaurantEntity)).thenReturn(restaurantEntityUpdated);
        Mockito.when(restaurantRepository.save(restaurantEntityUpdated)).thenReturn(restaurantEntityUpdated);
        Mockito.when(restaurantDtoConverter.convert(restaurantEntityUpdated)).thenReturn(restaurantUpdated);

        RestaurantDto resultRestaurant = restaurantService.updateRestaurantStatus(restaurantUpdateRequest);

        Assertions.assertNotEquals(RestaurantStatus.CANCELED, restaurant.getRestaurantStatus());
        Assertions.assertEquals(RestaurantStatus.CANCELED, resultRestaurant.getRestaurantStatus());

    }

    @Test(expected = EntityNotFoundException.class)
    public void giveInvalidId_whenUpdateRestaurant_thenThrowEntityNotFoundException(){
        RestaurantUpdateRequest restaurantUpdateRequest = RestaurantUpdateRequest.builder().id(1L).build();

        Mockito.doThrow(NullPointerException.class).when(restaurantRepository).getById(anyLong());
        restaurantService.updateRestaurantStatus(restaurantUpdateRequest);

    }

    @Test
    public void whenIsRestaurantStatusApproved_thenReturnTrue(){
        RestaurantEntity restaurantEntity = RestaurantEntity.builder()
                .id(1L).restaurantStatus(RestaurantStatus.APPROVED).build();

        Mockito.when(restaurantRepository.getById(1L)).thenReturn(restaurantEntity);

        Boolean result = restaurantService.isRestaurantApproved(1L);

        Assertions.assertEquals(true, result);
    }

    @Test
    public void whenIsRestaurantStatusNotApproved_thenReturnFalse(){
        RestaurantEntity restaurantEntity = RestaurantEntity.builder()
                .id(1L).restaurantStatus(RestaurantStatus.CANCELED).build();

        Mockito.when(restaurantRepository.getById(1L)).thenReturn(restaurantEntity);

        Boolean result = restaurantService.isRestaurantApproved(1L);

        Assertions.assertEquals(false, result);
    }

}