package com.finartz.restaurantapp.service.impl;

import com.finartz.restaurantapp.exception.EntityNotFoundException;
import com.finartz.restaurantapp.model.converter.dtoconverter.UserDtoConverter;
import com.finartz.restaurantapp.model.converter.entityconverter.fromcreaterequest.UserCreateRequestToEntityConverter;
import com.finartz.restaurantapp.model.dto.UserDto;
import com.finartz.restaurantapp.model.entity.UserEntity;
import com.finartz.restaurantapp.model.request.create.UserCreateRequest;
import com.finartz.restaurantapp.repository.UserRepository;
import com.finartz.restaurantapp.service.AddressService;
import com.finartz.restaurantapp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserDtoConverter userDtoConverter;
    private final UserCreateRequestToEntityConverter userCreateRequestToEntityConverter;
    private final AddressService addressService;

    @Override
    public UserDetails loadUserByUsername(String email) throws EntityNotFoundException {
        UserEntity userEntity = userRepository.findByEmail(email);
        if(userEntity == null)
            throw new EntityNotFoundException("User not found in database");
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        userEntity.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role.toString()));
        });
        return new org.springframework.security.core.userdetails.User(userEntity.getEmail(), userEntity.getPassword(), authorities);
    }

    @Override
    public UserDto getUser(Long id){
        return userDtoConverter.convert(userRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Not found User with id: " + id)
        ));
    }

    @Override
    public UserDto getUser(String email) {
        UserEntity userEntity = userRepository.findByEmail(email);
        if (Objects.isNull(userEntity))
            throw new EntityNotFoundException("Not found User with email: " + email);
        return userDtoConverter.convert(userEntity);
    }

    @Override
    @Transactional
    public UserDto createUser(UserCreateRequest userCreateRequest){
        userCreateRequest.setPassword(passwordEncoder.encode(userCreateRequest.getPassword()));
        UserEntity userEntity = userRepository.save(userCreateRequestToEntityConverter.convert(userCreateRequest));

        if(Objects.nonNull(userCreateRequest.getAddressCreateRequest())){
            userCreateRequest.getAddressCreateRequest().setUserId(userEntity.getId());
            addressService.createAddress(userCreateRequest.getAddressCreateRequest());
        }
        return userDtoConverter.convert(userEntity);
    }

}
