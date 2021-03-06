package com.finartz.restaurantapp.model.converter.dtoconverter;

import com.finartz.restaurantapp.exception.EntityNotFoundException;
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
        if (Objects.isNull(userEntity)){
            throw new EntityNotFoundException("Not found user entity");
        }

        UserDto userDto = new UserDto();

        userDto.setId(userEntity.getId());
        userDto.setName(userEntity.getName());
        userDto.setEmail(userEntity.getEmail());
        userDto.setRoles(userEntity.getRoles());

        return userDto;
    }

}
