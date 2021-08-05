package com.finartz.restaurantapp.service.impl;

import com.finartz.restaurantapp.model.entity.UserEntity;
import com.finartz.restaurantapp.repository.UserRepository;
import com.finartz.restaurantapp.service.UserService;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

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
    public List<UserEntity> getUsers(){
        return userRepository.findAll();
    }

    @Override
    public UserEntity getUser(Long id){
        return userRepository.getById(id);
    }

    @Override
    public UserEntity getUser(String email){
        return userRepository.findByEmail(email);
    }

    @Override
    public UserEntity createUser(UserEntity userEntity){
        userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
        return userRepository.save(userEntity);
    }

    @Override
    public UserEntity updateUser(UserEntity userEntity){
        UserEntity foundUserEntity = userRepository.getById(userEntity.getId());

        if(userEntity.getEmail() != null)
            foundUserEntity.setEmail(userEntity.getEmail());
        if(userEntity.getName() != null)
            foundUserEntity.setName(userEntity.getName());
        if(userEntity.getPassword() != null)
            foundUserEntity.setPassword(userEntity.getPassword());
        if(userEntity.getAddressEntities() != null)
            foundUserEntity.setAddressEntities(userEntity.getAddressEntities());
        if(userEntity.getCommentEntities() != null)
            foundUserEntity.setCommentEntities(userEntity.getCommentEntities());
        if(userEntity.getRestaurantEntities() != null)
            foundUserEntity.setRestaurantEntities(userEntity.getRestaurantEntities());

        return userRepository.save(foundUserEntity);
    }

    @Override
    public UserEntity deleteUser(Long id){
        UserEntity userEntity = userRepository.getById(id);
        if (userEntity != null) {
            userRepository.deleteById(id);
            return userEntity;
        }
        return userEntity;
    }

}
