package com.finartz.restaurantapp.service;

import com.finartz.restaurantapp.exception.EntityNotFoundException;
import com.finartz.restaurantapp.model.converter.dtoconverter.UserDtoConverter;
import com.finartz.restaurantapp.model.converter.entityconverter.fromcreaterequest.UserCreateRequestToEntityConverter;
import com.finartz.restaurantapp.model.dto.AddressDto;
import com.finartz.restaurantapp.model.dto.UserDto;
import com.finartz.restaurantapp.model.entity.UserEntity;
import com.finartz.restaurantapp.model.enumerated.Role;
import com.finartz.restaurantapp.model.request.create.AddressCreateRequest;
import com.finartz.restaurantapp.model.request.create.UserCreateRequest;
import com.finartz.restaurantapp.repository.UserRepository;
import com.finartz.restaurantapp.service.impl.AddressServiceImpl;
import com.finartz.restaurantapp.service.impl.UserServiceImpl;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    private final static String NAME_ALI_AKAY = "Ali Akay";
    private final static String EMAIL_ALI_GMAIL_COM = "ali@gmail.com";

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private AddressServiceImpl addressService;

    @Mock
    private UserDetailsService userDetailsService;

    @Mock
    private UserDtoConverter userDtoConverter;

    @Mock
    private UserCreateRequestToEntityConverter userCreateRequestToEntityConverter;

    @Test
    public void whenFetchByValidId_thenReturnUser() {
        UserEntity userEntity = UserEntity.builder().name(NAME_ALI_AKAY).build();
        UserDto user = UserDto.builder().name(NAME_ALI_AKAY).build();

        Mockito.when(userRepository.findById(1L)).thenReturn(Optional.ofNullable(userEntity));
        Mockito.when(userDtoConverter.convert(userEntity)).thenReturn(user);

        UserDto resultUser = userService.getUser(1L);

        assertEquals(user.getId(), resultUser.getId());
    }

    @Test(expected = EntityNotFoundException.class)
    public void whenFetchByInvalidId_thenThrowEntityNotFoundException() {

        Mockito.when(userRepository.findById(anyLong())).thenReturn(Optional.empty());
        userService.getUser(anyLong());

    }

    @Test
    public void whenFetchByEmail_thenReturnUser() {
        UserEntity userEntity = UserEntity.builder().name(NAME_ALI_AKAY).email(EMAIL_ALI_GMAIL_COM).build();
        UserDto user = UserDto.builder().email(EMAIL_ALI_GMAIL_COM).name(NAME_ALI_AKAY).build();

        Mockito.when(userRepository.findByEmail(EMAIL_ALI_GMAIL_COM)).thenReturn(userEntity);
        Mockito.when(userDtoConverter.convert(userEntity)).thenReturn(user);

        UserDto resultUser = userService.getUser(EMAIL_ALI_GMAIL_COM);

        assertEquals(user.getEmail(), resultUser.getEmail());
    }

    @Test(expected = EntityNotFoundException.class)
    public void whenFetchByInvalidMail_thenThrowEntityNotFoundException() {

        Mockito.when(userRepository.findByEmail(anyString())).thenReturn(null);
        userService.getUser(anyString());

    }

    @Test
    public void whenAddUser_thenReturnSavedUser() {
        AddressCreateRequest addressCreateRequest = AddressCreateRequest.builder().build();
        AddressDto addressDto = AddressDto.builder().build();
        UserEntity userEntity = UserEntity.builder().name(NAME_ALI_AKAY).build();
        UserDto user = UserDto.builder().name(NAME_ALI_AKAY).build();
        UserCreateRequest userCreateRequest = UserCreateRequest
                .builder()
                .name(NAME_ALI_AKAY)
                .addressCreateRequest(addressCreateRequest)
                .build();

        Mockito.when(userCreateRequestToEntityConverter.convert(userCreateRequest)).thenReturn(userEntity);
        Mockito.when(passwordEncoder.encode(userEntity.getPassword())).thenReturn(userEntity.getPassword());
        Mockito.when(userRepository.save(userEntity)).thenReturn(userEntity);
        Mockito.when(userDtoConverter.convert(userEntity)).thenReturn(user);
        Mockito.when(addressService.createAddress(addressCreateRequest)).thenReturn(addressDto);

        UserDto resultUser = userService.createUser(userCreateRequest);

        assertEquals(userEntity.getName(), resultUser.getName());
    }

    @Test
    public void giveValidUsername_whenFetchByUsername_thenReturnSpringUser(){
        UserEntity userEntity = UserEntity.builder().email("name").password("password").roles(Arrays.asList(Role.USER)).build();

        Mockito.when(userRepository.findByEmail(anyString())).thenReturn(userEntity);
        UserDetails userDetails = userService.loadUserByUsername(anyString());

        Assertions.assertEquals(userDetails.getUsername(), userEntity.getEmail());
    }

    @Test(expected = EntityNotFoundException.class)
    public void giveInvalidUsername_whenFetchByUsername_thenReturnSpringUser(){

        Mockito.when(userRepository.findByEmail(anyString())).thenReturn(null);
        userService.loadUserByUsername(anyString());

    }

}