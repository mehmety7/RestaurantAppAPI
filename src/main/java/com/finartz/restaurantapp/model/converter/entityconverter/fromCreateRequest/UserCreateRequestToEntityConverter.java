package com.finartz.restaurantapp.model.converter.entityconverter.fromCreateRequest;

import com.finartz.restaurantapp.exception.EntityNotFoundException;
import com.finartz.restaurantapp.model.converter.GenericConverter;
import com.finartz.restaurantapp.model.entity.AddressEntity;
import com.finartz.restaurantapp.model.entity.UserEntity;
import com.finartz.restaurantapp.model.request.create.AddressCreateRequest;
import com.finartz.restaurantapp.model.request.create.UserCreateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class UserCreateRequestToEntityConverter implements GenericConverter<UserCreateRequest, UserEntity> {

    private final GenericConverter<AddressCreateRequest,AddressEntity> addressCreateRequestAddressEntityGenericConverter;

    @Override
    public UserEntity convert(final UserCreateRequest userCreateRequest){
        if (Objects.isNull(userCreateRequest)){
            throw new EntityNotFoundException("Not found user create request");
        }

        UserEntity userEntity = new UserEntity();

        userEntity.setName(userCreateRequest.getName());
        userEntity.setEmail(userCreateRequest.getEmail());
        userEntity.setPassword(userCreateRequest.getPassword());
        userEntity.setRoles(userCreateRequest.getRoles());

        if(Objects.nonNull(userCreateRequest.getAddressCreateRequest())) {
            userEntity.setAddressEntities(Arrays.asList(convert(userCreateRequest.getAddressCreateRequest())));
        }else{
            List<AddressEntity> addressEntities = new ArrayList<>();
            userEntity.setAddressEntities(addressEntities);
        }

        return userEntity;
    }

    private AddressEntity convert(final AddressCreateRequest addressCreateRequest) {
        return addressCreateRequestAddressEntityGenericConverter.convert(addressCreateRequest);
    }

}