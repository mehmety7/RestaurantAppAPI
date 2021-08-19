package com.finartz.restaurantapp.model.converter.entityconverter.fromCreateRequest;

import com.finartz.restaurantapp.model.converter.GenericConverter;
import com.finartz.restaurantapp.model.entity.RestaurantEntity;
import com.finartz.restaurantapp.model.entity.UserEntity;
import com.finartz.restaurantapp.model.request.create.RestaurantCreateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@RequiredArgsConstructor
public class RestaurantCreateRequestToEntityConverter implements GenericConverter<RestaurantCreateRequest, RestaurantEntity> {

    @Override
    public RestaurantEntity convert(final RestaurantCreateRequest restaurantCreateRequest){
        if(Objects.isNull(restaurantCreateRequest)){
            return null;
        }

        RestaurantEntity restaurantEntity = new RestaurantEntity();

        restaurantEntity.setName(restaurantCreateRequest.getName());
        restaurantEntity.setStatus(restaurantCreateRequest.getStatus());

        UserEntity userEntity = new UserEntity();
        userEntity.setId(restaurantCreateRequest.getUserId());
        restaurantEntity.setUserEntity(userEntity);

        return restaurantEntity;
    }

}