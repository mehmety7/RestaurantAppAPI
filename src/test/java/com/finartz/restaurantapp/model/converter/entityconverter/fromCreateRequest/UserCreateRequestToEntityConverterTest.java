package com.finartz.restaurantapp.model.converter.entityconverter.fromCreateRequest;

import com.finartz.restaurantapp.model.entity.AddressEntity;
import com.finartz.restaurantapp.model.entity.UserEntity;
import com.finartz.restaurantapp.model.request.create.AddressCreateRequest;
import com.finartz.restaurantapp.model.request.create.UserCreateRequest;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class UserCreateRequestToEntityConverterTest {

    @Spy
    @InjectMocks
    private UserCreateRequestToEntityConverter userCreateRequestToEntityConverter;

    @Mock
    private AddressCreateRequestToEntityConverter addressCreateRequestToEntityConverter;

    @Test
    public void whenPassValidUserCreateRequest_thenReturnUserEntity(){

        AddressCreateRequest addressCreateRequest = AddressCreateRequest.builder().build();
        AddressEntity addressEntity = AddressEntity.builder().build();

        UserCreateRequest userCreateRequest = UserCreateRequest.builder()
                .name("User")
                .addressCreateRequest(addressCreateRequest)
                .build();

        Mockito.when(addressCreateRequestToEntityConverter.convert(addressCreateRequest)).thenReturn(addressEntity);
        UserEntity userEntity = userCreateRequestToEntityConverter.convert(userCreateRequest);

        Assertions.assertEquals(userEntity.getName(), userCreateRequest.getName());
    }

}