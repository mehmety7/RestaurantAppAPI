package com.finartz.restaurantapp.model.converter.entityconverter.fromCreateRequest;

import com.finartz.restaurantapp.exception.EntityNotFoundException;
import com.finartz.restaurantapp.model.entity.RestaurantEntity;
import com.finartz.restaurantapp.model.request.create.RestaurantCreateRequest;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class RestaurantCreateRequestToEntityConverterTest {

    @Spy
    private RestaurantCreateRequestToEntityConverter restaurantCreateRequestToEntityConverter;

    @Test
    public void whenPassValidRestaurantCreateRequest_thenReturnRestaurantEntity(){
        RestaurantCreateRequest restaurantCreateRequest = RestaurantCreateRequest.builder()
                .name("Restaurant")
                .userId(1l)
                .build();

        RestaurantEntity restaurantEntity = restaurantCreateRequestToEntityConverter.convert(restaurantCreateRequest);

        Assertions.assertEquals(restaurantEntity.getName(), restaurantCreateRequest.getName());
    }

    @Test(expected = EntityNotFoundException.class)
    public void whenPassNullRestaurantCreateRequest_thenThrowEntityNotFoundException(){
        restaurantCreateRequestToEntityConverter.convert(null);
    }

}
