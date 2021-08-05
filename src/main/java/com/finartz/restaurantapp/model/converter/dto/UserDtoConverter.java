package com.finartz.restaurantapp.model.converter.dto;

import com.finartz.restaurantapp.model.converter.GenericConverter;
import com.finartz.restaurantapp.model.dto.UserDto;
import com.finartz.restaurantapp.model.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserDtoConverter implements GenericConverter<UserEntity, UserDto> {

//    private final GenericConverter<AddressEntity, AddressDto> addressDtoConverter;
//    private final GenericConverter<CommentEntity, CommentDto> commentDtoConverter;
//    private final GenericConverter<RestaurantEntity, RestaurantDto> restaurantDtoConverter;

    @Override
    public UserDto convert(final UserEntity userEntity){
        if(userEntity == null){
            return null;
        }

        UserDto userDto = new UserDto();

        userDto.setId(userEntity.getId());
        userDto.setName(userEntity.getName());
        userDto.setEmail(userEntity.getEmail());
        userDto.setPassword(userEntity.getPassword());
        userDto.setRoles(userEntity.getRoles());

//        List<AddressDto> addresses = new ArrayList<>();
//        userEntity.getAddressEntities().forEach(addressEntity -> {
//            addresses.add(convert(addressEntity));
//        });
//        userDto.setAddresses(addresses);
//
//        List<CommentDto> comments = new ArrayList<>();
//        userEntity.getCommentEntities().forEach(commentEntity -> {
//            comments.add(convert(commentEntity));
//        });
//        userDto.setComments(comments);
//
//        List<RestaurantDto> restaurants = new ArrayList<>();
//        userEntity.getRestaurantEntities().forEach(restaurantEntity -> {
//            restaurants.add(convert(restaurantEntity));
//        });
//        userDto.setRestaurants(restaurants);

        return userDto;
    }

//    private AddressDto convert(final AddressEntity addressEntity){
//        return addressDtoConverter.convert(addressEntity);
//    }
//
//    private CommentDto convert(final CommentEntity commentEntity){
//        return commentDtoConverter.convert(commentEntity);
//    }
//
//    private RestaurantDto convert(final RestaurantEntity restaurantEntity){
//        return restaurantDtoConverter.convert(restaurantEntity);
//    }

}
