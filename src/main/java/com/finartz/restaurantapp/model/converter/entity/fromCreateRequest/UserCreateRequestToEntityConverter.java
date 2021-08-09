package com.finartz.restaurantapp.model.converter.entity.fromCreateRequest;

import com.finartz.restaurantapp.model.converter.GenericConverter;
import com.finartz.restaurantapp.model.dto.AddressDto;
import com.finartz.restaurantapp.model.dto.CommentDto;
import com.finartz.restaurantapp.model.dto.RestaurantDto;
import com.finartz.restaurantapp.model.entity.AddressEntity;
import com.finartz.restaurantapp.model.entity.CommentEntity;
import com.finartz.restaurantapp.model.entity.RestaurantEntity;
import com.finartz.restaurantapp.model.entity.UserEntity;
import com.finartz.restaurantapp.model.request.create.UserCreateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class UserCreateRequestToEntityConverter implements GenericConverter<UserCreateRequest, UserEntity> {

    private final GenericConverter<AddressDto, AddressEntity> addressEntityConverter;
    private final GenericConverter<RestaurantDto, RestaurantEntity> restaurantEntityConverter;
    private final GenericConverter<CommentDto, CommentEntity> commentEntityConverter;

    @Override
    public UserEntity convert(final UserCreateRequest userCreateRequest){
        if(userCreateRequest == null){
            return null;
        }

        UserEntity userEntity = new UserEntity();

        userEntity.setName(userCreateRequest.getName());
        userEntity.setEmail(userCreateRequest.getEmail());
        userEntity.setPassword(userCreateRequest.getPassword());
        userEntity.setRoles(userCreateRequest.getRoles());

        List<AddressEntity> addressEntities = new ArrayList<>();
        userCreateRequest.getAddresses().forEach(address -> {
            addressEntities.add(convert(address));
        });
        userEntity.setAddressEntities(addressEntities);

        List<RestaurantEntity> restaurantEntities = new ArrayList<>();
        userCreateRequest.getRestaurants().forEach(restaurant -> {
            restaurantEntities.add(convert(restaurant));
        });
        userEntity.setRestaurantEntities(restaurantEntities);

        List<CommentEntity> commentEntities = new ArrayList<>();
        userCreateRequest.getComments().forEach(comment -> {
            commentEntities.add(convert(comment));
        });
        userEntity.setCommentEntities(commentEntities);

        return userEntity;
    }

    private AddressEntity convert(final AddressDto address){
        return addressEntityConverter.convert(address);
    }

    private RestaurantEntity convert(final RestaurantDto restaurant){
        return restaurantEntityConverter.convert(restaurant);
    }

    private CommentEntity convert(final CommentDto comment){
        return commentEntityConverter.convert(comment);
    }

}