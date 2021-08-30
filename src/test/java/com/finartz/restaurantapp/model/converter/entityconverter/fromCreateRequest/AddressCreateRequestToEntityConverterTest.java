package com.finartz.restaurantapp.model.converter.entityconverter.fromCreateRequest;

import com.finartz.restaurantapp.exception.EntityNotFoundException;
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
    public void whenPassValidUserAddressCreateRequest_thenReturnAddressEntity(){
        AddressCreateRequest addressCreateRequest = AddressCreateRequest.builder()
                .name("Address")
                .cityId(1L)
                .countyId(1L)
                .branchId(1L)
                .build();

        AddressEntity addressEntity = addressCreateRequestToEntityConverter.convert(addressCreateRequest);

        Assertions.assertEquals(addressEntity.getName(), addressCreateRequest.getName());
    }

    @Test
    public void whenPassValidBranchAddressCreateRequest_thenReturnAddressEntity(){
        AddressCreateRequest addressCreateRequest = AddressCreateRequest.builder()
                .name("Address")
                .cityId(1L)
                .countyId(1L)
                .userId(1L)
                .build();

        AddressEntity addressEntity = addressCreateRequestToEntityConverter.convert(addressCreateRequest);

        Assertions.assertEquals(addressEntity.getName(), addressCreateRequest.getName());
    }

    @Test(expected = EntityNotFoundException.class)
    public void whenPassNullAddressCreateRequest_thenThrowEntityNotFoundException(){
        addressCreateRequestToEntityConverter.convert(null);
    }

}
