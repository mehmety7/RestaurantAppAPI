package com.finartz.restaurantapp.service;

import com.finartz.restaurantapp.model.converter.dto.UserDtoConverter;
import com.finartz.restaurantapp.model.converter.entity.fromCreateRequest.UserCreateRequestToEntityConverter;
import com.finartz.restaurantapp.model.dto.UserDto;
import com.finartz.restaurantapp.model.entity.UserEntity;
import com.finartz.restaurantapp.model.request.create.UserCreateRequest;
import com.finartz.restaurantapp.repository.UserRepository;
import com.finartz.restaurantapp.service.impl.UserServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    private final static String NAME_ALI_AKAY = "Ali Akay";
    private final static String NAME_VELI_KAZAK = "Veli Kazak";
    private final static String EMAIL_ALI_GMAIL_COM = "ali@gmail.com";

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private UserDtoConverter userDtoConverter;

    @Mock
    private UserCreateRequestToEntityConverter userCreateRequestToEntityConverter;

    @Test
    public void whenFetchById_thenReturnUser() {
        UserEntity userEntity = UserEntity.builder().name(NAME_ALI_AKAY).build();
        UserDto user = UserDto.builder().name(NAME_ALI_AKAY).build();

        Mockito.when(userRepository.getById(1L)).thenReturn(userEntity);
        Mockito.when(userDtoConverter.convert(userEntity)).thenReturn(user);

        UserDto resultUser = userService.getUser(1L);

        assertEquals(user.getId(), resultUser.getId());
    }

    @Test
    public void whenAddUser_thenReturnSavedUser() {
        UserEntity userEntity = UserEntity.builder().name(NAME_ALI_AKAY).build();
        UserDto user = UserDto.builder().name(NAME_ALI_AKAY).build();
        UserCreateRequest userCreateRequest = UserCreateRequest.builder().name(NAME_ALI_AKAY).build();

        Mockito.when(userCreateRequestToEntityConverter.convert(userCreateRequest)).thenReturn(userEntity);
        Mockito.when(passwordEncoder.encode(userEntity.getPassword())).thenReturn(userEntity.getPassword());
        Mockito.when(userRepository.save(userEntity)).thenReturn(userEntity);
        Mockito.when(userDtoConverter.convert(userEntity)).thenReturn(user);

        UserDto resultUser = userService.createUser(userCreateRequest);

        assertEquals(userEntity.getName(), resultUser.getName());
    }

}