package com.finartz.restaurantapp.model.converter.entityconverter.fromupdaterequest;

import com.finartz.restaurantapp.exception.EntityNotFoundException;
import com.finartz.restaurantapp.model.entity.RestaurantEntity;
import com.finartz.restaurantapp.model.enumerated.Status;
import com.finartz.restaurantapp.model.request.update.RestaurantUpdateRequest;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class RestaurantUpdateRequestToEntityConverterTest {

    @Spy
    private RestaurantUpdateRequestToEntityConverter restaurantUpdateRequestToEntityConverter;

    @Test
    public void whenPassValidRestaurantUpdateRequest_thenReturnRestaurantEntity(){
        RestaurantUpdateRequest restaurantUpdateRequest = RestaurantUpdateRequest.builder()
                .status(Status.CANCELED)
                .build();

        RestaurantEntity restaurantExisting = RestaurantEntity.builder().status(Status.WAITING).build();
        Status previousStatus = restaurantExisting.getStatus();

        RestaurantEntity restaurantUpdate = restaurantUpdateRequestToEntityConverter.convert(restaurantUpdateRequest, restaurantExisting);

        Assertions.assertEquals(restaurantUpdate.getStatus(), restaurantUpdateRequest.getStatus());
        Assertions.assertEquals(restaurantUpdate.getStatus(), restaurantExisting.getStatus());
        Assertions.assertNotEquals(restaurantUpdate.getStatus(), previousStatus);

    }

    @Test(expected = EntityNotFoundException.class)
    public void whenPassNullRestaurantUpdateRequest_thenThrowEntityNotFoundException(){
        restaurantUpdateRequestToEntityConverter.convert(null, RestaurantEntity.builder().build());
    }

    @Test(expected = EntityNotFoundException.class)
    public void whenPassNullRestaurantExisting_thenThrowEntityNotFoundException(){
        restaurantUpdateRequestToEntityConverter.convert(RestaurantUpdateRequest.builder().build(), null);
    }

}
