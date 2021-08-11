package com.finartz.restaurantapp.model.converter.entityconverter.fromCreateRequest;

import com.finartz.restaurantapp.model.converter.GenericConverter;
import com.finartz.restaurantapp.model.dto.AddressDto;
import com.finartz.restaurantapp.model.entity.AddressEntity;
import com.finartz.restaurantapp.model.entity.UserEntity;
import com.finartz.restaurantapp.model.request.create.UserCreateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@RequiredArgsConstructor
public class UserCreateRequestToEntityConverter implements GenericConverter<UserCreateRequest, UserEntity> {

    private final GenericConverter<AddressDto, AddressEntity> addressEntityConverter;

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
        userEntity.setAddressEntities(Arrays.asList(convert(userCreateRequest.getAddress())));

        return userEntity;
    }

    private AddressEntity convert(final AddressDto address){
        return addressEntityConverter.convert(address);
    }

}