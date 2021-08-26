package com.finartz.restaurantapp.model.converter.dtoconverter;

import com.finartz.restaurantapp.model.converter.GenericConverter;
import com.finartz.restaurantapp.model.dto.UserDto;
import com.finartz.restaurantapp.model.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

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

        if(Objects.nonNull(userEntity.getId())){
            userDto.setId(userEntity.getId());
        }
        if(Objects.nonNull(userEntity.getName())){
            userDto.setName(userEntity.getName());
        }
        if(Objects.nonNull(userEntity.getEmail())){
            userDto.setEmail(userEntity.getEmail());
        }
        if(Objects.nonNull(userEntity.getRoles())){
            userDto.setRoles(userEntity.getRoles());
        }

        return userDto;
    }

}
