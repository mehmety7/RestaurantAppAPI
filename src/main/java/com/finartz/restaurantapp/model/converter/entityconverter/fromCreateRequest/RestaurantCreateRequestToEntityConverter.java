package com.finartz.restaurantapp.model.converter.entityconverter.fromCreateRequest;

import com.finartz.restaurantapp.model.converter.GenericConverter;
import com.finartz.restaurantapp.model.dto.UserDto;
import com.finartz.restaurantapp.model.entity.RestaurantEntity;
import com.finartz.restaurantapp.model.entity.UserEntity;
import com.finartz.restaurantapp.model.request.create.RestaurantCreateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RestaurantCreateRequestToEntityConverter implements GenericConverter<RestaurantCreateRequest, RestaurantEntity> {

    private final GenericConverter<UserDto, UserEntity> userEntityConverter;

    @Override
    public RestaurantEntity convert(final RestaurantCreateRequest restaurantCreateRequest){
        if(restaurantCreateRequest == null){
            return null;
        }

        RestaurantEntity restaurantEntity = new RestaurantEntity();

        restaurantEntity.setName(restaurantCreateRequest.getName());
        restaurantEntity.setStatus(restaurantCreateRequest.getStatus());
        restaurantEntity.setUserEntity(convert(restaurantCreateRequest.getUser()));

        return restaurantEntity;
    }

    private UserEntity convert(final UserDto user){
        return userEntityConverter.convert(user);
    }

}