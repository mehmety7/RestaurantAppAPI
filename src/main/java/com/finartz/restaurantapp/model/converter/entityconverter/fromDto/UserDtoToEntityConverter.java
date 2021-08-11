package com.finartz.restaurantapp.model.converter.entityconverter.fromDto;

import com.finartz.restaurantapp.model.converter.GenericConverter;
import com.finartz.restaurantapp.model.dto.UserDto;
import com.finartz.restaurantapp.model.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserDtoToEntityConverter implements GenericConverter<UserDto, UserEntity> {

//    private final GenericConverter<AddressDto, AddressEntity> addressEntityConverter;
//    private final GenericConverter<RestaurantDto, RestaurantEntity> restaurantEntityConverter;
//    private final GenericConverter<CommentDto, CommentEntity> commentEntityConverter;

    @Override
    public UserEntity convert(final UserDto user){
        if(user == null){
            return null;
        }

        UserEntity userEntity = new UserEntity();

        userEntity.setId(user.getId());
        userEntity.setName(user.getName());
        userEntity.setEmail(user.getEmail());
//        userEntity.setPassword(user.getPassword());
        userEntity.setRoles(user.getRoles());

//        List<AddressEntity> addressEntities = new ArrayList<>();
//        user.getAddresses().forEach(address -> {
//            addressEntities.add(convert(address));
//        });
//        userEntity.setAddressEntities(addressEntities);
//
//        List<RestaurantEntity> restaurantEntities = new ArrayList<>();
//        user.getRestaurants().forEach(restaurant -> {
//            restaurantEntities.add(convert(restaurant));
//        });
//        userEntity.setRestaurantEntities(restaurantEntities);
//
//        List<CommentEntity> commentEntities = new ArrayList<>();
//        user.getComments().forEach(comment -> {
//            commentEntities.add(convert(comment));
//        });
//        userEntity.setCommentEntities(commentEntities);

        return userEntity;
    }

//    private AddressEntity convert(final AddressDto address){
//        return addressEntityConverter.convert(address);
//    }
//
//    private RestaurantEntity convert(final RestaurantDto restaurant){
//        return restaurantEntityConverter.convert(restaurant);
//    }
//
//    private CommentEntity convert(final CommentDto comment){
//        return commentEntityConverter.convert(comment);
//    }

}