package com.finartz.restaurantapp.service.impl;

import com.finartz.restaurantapp.model.User;
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
        User user = userRepository.findByEmail(email);
        if(user == null)
            throw new UsernameNotFoundException("User not found in database");
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role.toString()));
        });
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), authorities);

    }

    @Override
    public User create(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public List<User> getAll(){
        return userRepository.findAll();
    }

    @Override
    public User getById(Long id){
        return userRepository.getById(id);
    }

    @Override
    public User findByEmail(String email){
        return userRepository.findByEmail(email);
    }

    @Override
    public User update(User user){
        User foundUser = userRepository.getById(user.getId());

        if(user.getEmail() != null)
            foundUser.setEmail(user.getEmail());
        if(user.getName() != null)
            foundUser.setName(user.getName());
        if(user.getPassword() != null)
            foundUser.setPassword(user.getPassword());
        if(user.getAddressList() != null)
            foundUser.setAddressList(user.getAddressList());
        if(user.getCreditCardList() != null)
            foundUser.setCreditCardList(user.getCreditCardList());
        if(user.getCommentList() != null)
            foundUser.setCommentList(user.getCommentList());
        if(user.getRestaurantList() != null)
            foundUser.setRestaurantList(user.getRestaurantList());

        return userRepository.save(foundUser);
    }

    @Override
    public User deleteById(Long id){
        User user = userRepository.getById(id);
        if (user != null) {
            userRepository.deleteById(id);
            return user;
        }
        return user;
    }

}
