package com.finartz.restaurantapp.model.converter.dtoconverter;

import com.finartz.restaurantapp.exception.EntityNotFoundException;
import com.finartz.restaurantapp.model.dto.UserDto;
import com.finartz.restaurantapp.model.entity.UserEntity;
import com.finartz.restaurantapp.model.enumerated.Role;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;

@RunWith(MockitoJUnitRunner.class)
public class UserDtoConverterTest {

    @Spy
    private UserDtoConverter userDtoConverter;

    @Test
    public void whenPassValidUserEntity_thenReturnUserDto(){
        UserEntity userEntity = UserEntity.builder()
                .id(1l)
                .name("User")
                .roles(Arrays.asList(Role.USER))
                .build();

        UserDto userDto = userDtoConverter.convert(userEntity);

        Assertions.assertEquals(userDto.getName(), userEntity.getName());

    }

    @Test(expected = EntityNotFoundException.class)
    public void whenPassNullUserEntity_thenReturnThrowEntityNotFoundException(){
        userDtoConverter.convert(null);
    }

}
