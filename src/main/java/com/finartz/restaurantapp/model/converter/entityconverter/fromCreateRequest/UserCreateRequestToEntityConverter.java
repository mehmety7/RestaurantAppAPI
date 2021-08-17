package com.finartz.restaurantapp.model.converter.entityconverter.fromCreateRequest;

import com.finartz.restaurantapp.model.converter.GenericConverter;
import com.finartz.restaurantapp.model.entity.AddressEntity;
import com.finartz.restaurantapp.model.entity.UserEntity;
import com.finartz.restaurantapp.model.request.create.AddressCreateRequest;
import com.finartz.restaurantapp.model.request.create.UserCreateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@RequiredArgsConstructor
public class UserCreateRequestToEntityConverter implements GenericConverter<UserCreateRequest, UserEntity> {

    private final GenericConverter<AddressCreateRequest, AddressEntity> addressCreateRequestAddressEntityGenericConverter;

    @Override
    public UserEntity convert(final UserCreateRequest userCreateRequest){
        if(userCreateRequest == null){
            return null;
        }

        UserEntity userEntity = new UserEntity();

        userEntity.setName(userCreateRequest.getName());
        userEntity.setEmail(userCreateRequest.getEmail());
        userEntity.setPassword(userCreateRequest.getPassword());
        userEntity.setRoles(Arrays.asList(userCreateRequest.getRole()));
        userEntity.setAddressEntities(Arrays.asList(convert(userCreateRequest.getAddressCreateRequest())));

        return userEntity;
    }

    private AddressEntity convert(final AddressCreateRequest addressCreateRequest){
        return addressCreateRequestAddressEntityGenericConverter.convert(addressCreateRequest);
    }

}