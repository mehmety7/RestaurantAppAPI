package com.finartz.restaurantapp.service;

import com.finartz.restaurantapp.model.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String email);

    public User create(User user);

    public List<User> getAll();

    public User getById(Long id);

    public User findByEmail(String email);

    public User update(User user);

    public User deleteById(Long id);

}
