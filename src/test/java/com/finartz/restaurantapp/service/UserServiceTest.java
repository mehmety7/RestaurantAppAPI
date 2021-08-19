package com.finartz.restaurantapp.service;

import com.finartz.restaurantapp.model.converter.dtoconverter.UserDtoConverter;
import com.finartz.restaurantapp.model.converter.entityconverter.fromCreateRequest.UserCreateRequestToEntityConverter;
import com.finartz.restaurantapp.model.dto.AddressDto;
import com.finartz.restaurantapp.model.dto.UserDto;
import com.finartz.restaurantapp.model.entity.UserEntity;
import com.finartz.restaurantapp.model.request.create.AddressCreateRequest;
import com.finartz.restaurantapp.model.request.create.UserCreateRequest;
import com.finartz.restaurantapp.repository.UserRepository;
import com.finartz.restaurantapp.service.impl.AddressServiceImpl;
import com.finartz.restaurantapp.service.impl.UserServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
    private Validator validator;

    @Mock
    private AddressServiceImpl addressService;

    @Mock
    private UserDtoConverter userDtoConverter;

    @Mock
    private UserCreateRequestToEntityConverter userCreateRequestToEntityConverter;

    @Test
    public void whenFetchById_thenReturnUser() {
        UserEntity userEntity = UserEntity.builder().name(NAME_ALI_AKAY).build();
        UserDto user = UserDto.builder().name(NAME_ALI_AKAY).build();

        Mockito.when(userRepository.findById(1L)).thenReturn(Optional.ofNullable(userEntity));
        Mockito.when(userDtoConverter.convert(userEntity)).thenReturn(user);

        UserDto resultUser = userService.getUser(1L);

        assertEquals(user.getId(), resultUser.getId());
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

        Set<ConstraintViolation<AddressCreateRequest>> violations = Collections.emptySet();
        Mockito.when(validator.validate(userCreateRequest.getAddressCreateRequest())).thenReturn(violations);

        UserDto resultUser = userService.createUser(userCreateRequest);

        assertEquals(userEntity.getName(), resultUser.getName());
    }

}