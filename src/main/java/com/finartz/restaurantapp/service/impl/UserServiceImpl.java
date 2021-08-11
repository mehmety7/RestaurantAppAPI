package com.finartz.restaurantapp.service.impl;

import com.finartz.restaurantapp.model.converter.dtoconverter.UserDtoConverter;
import com.finartz.restaurantapp.model.converter.entityconverter.fromCreateRequest.UserCreateRequestToEntityConverter;
import com.finartz.restaurantapp.model.dto.UserDto;
import com.finartz.restaurantapp.model.entity.UserEntity;
import com.finartz.restaurantapp.model.request.create.UserCreateRequest;
import com.finartz.restaurantapp.repository.UserRepository;
import com.finartz.restaurantapp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserDtoConverter userDtoConverter;
    private final UserCreateRequestToEntityConverter userCreateRequestToEntityConverter;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByEmail(email);
        if(userEntity == null)
            throw new UsernameNotFoundException("User not found in database");
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        userEntity.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role.toString()));
        });
        return new org.springframework.security.core.userdetails.User(userEntity.getEmail(), userEntity.getPassword(), authorities);

    }

    @Override
    public UserDto getUser(Long id){
        return userDtoConverter.convert(userRepository.getById(id));
    }

    @Override
    public UserDto getUser(String email) {
        UserEntity userEntity = userRepository.findByEmail(email);
        return userDtoConverter.convert(userEntity);
    }

    @Override
    public UserDto createUser(UserCreateRequest userCreateRequest){
        userCreateRequest.setPassword(passwordEncoder.encode(userCreateRequest.getPassword()));
        UserEntity userEntity = userCreateRequestToEntityConverter.convert(userCreateRequest);
        return userDtoConverter.convert(userRepository.save(userEntity));
    }

}
