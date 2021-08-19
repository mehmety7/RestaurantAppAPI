package com.finartz.restaurantapp.model.converter.dtoconverter;

import com.finartz.restaurantapp.model.dto.UserDto;
import com.finartz.restaurantapp.model.entity.UserEntity;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class UserDtoConverterTest {

    @Spy
    private UserDtoConverter userDtoConverter;

    @Test
    public void whenPassValidUserEntity_thenReturnUserDto(){
        UserEntity userEntity = UserEntity.builder()
                .id(1l)
                .name("User")
                .build();

        UserDto userDto = userDtoConverter.convert(userEntity);

        Assertions.assertEquals(userDto.getName(), userEntity.getName());

    }

}
