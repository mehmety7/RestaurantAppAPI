package com.finartz.restaurantapp.model.converter.dtoconverter;

import com.finartz.restaurantapp.model.converter.GenericConverter;
import com.finartz.restaurantapp.model.dto.UserDto;
import com.finartz.restaurantapp.model.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class UserDtoConverter implements GenericConverter<UserEntity, UserDto> {

    @Override
    public UserDto convert(final UserEntity userEntity){
        if(Objects.isNull(userEntity)){
            return null;
        }

        UserDto userDto = new UserDto();

        userDto.setId(userEntity.getId());
        userDto.setName(userEntity.getName());
        userDto.setEmail(userEntity.getEmail());

        if(Objects.nonNull(userEntity.getRoles())){
            userDto.setRoles(userEntity.getRoles());
        }else{
            userDto.setRoles(new ArrayList<>());
        }

        return userDto;
    }

}
