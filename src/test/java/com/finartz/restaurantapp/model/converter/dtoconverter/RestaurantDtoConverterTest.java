package com.finartz.restaurantapp.model.converter.dtoconverter;

import com.finartz.restaurantapp.model.dto.RestaurantDto;
import com.finartz.restaurantapp.model.entity.RestaurantEntity;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class RestaurantDtoConverterTest {

    @Spy
    private RestaurantDtoConverter restaurantDtoConverter;

    @Test
    public void whenPassValidRestaurantEntity_thenReturnRestaurantDto(){
        RestaurantEntity restaurantEntity = RestaurantEntity.builder()
                .id(1l)
                .name("Restaurant")
                .build();

        RestaurantDto restaurantDto = restaurantDtoConverter.convert(restaurantEntity);

        Assertions.assertEquals(restaurantDto.getName(), restaurantEntity.getName());

    }

}
