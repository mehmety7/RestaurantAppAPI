package com.finartz.restaurantapp.model.converter.entityconverter.fromCreateRequest;

import com.finartz.restaurantapp.model.entity.AddressEntity;
import com.finartz.restaurantapp.model.request.create.AddressCreateRequest;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class AddressCreateRequestToEntityConverterTest {

    @Spy
    private AddressCreateRequestToEntityConverter addressCreateRequestToEntityConverter;

    @Test
    public void whenPassValidAddressCreateRequest_thenReturnAddressEntity(){
        AddressCreateRequest addressCreateRequest = AddressCreateRequest.builder()
                .name("Address")
                .build();

        AddressEntity addressEntity = addressCreateRequestToEntityConverter.convert(addressCreateRequest);

        Assertions.assertEquals(addressEntity.getName(), addressCreateRequest.getName());
    }

}
