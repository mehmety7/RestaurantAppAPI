package com.finartz.restaurantapp.service;

import com.finartz.restaurantapp.model.User;
import com.finartz.restaurantapp.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User create(User user){
        return userRepository.save(user);
    }

    public List<User> getAll(){
        return userRepository.findAll();
    }

    public User getById(Long id){
        return userRepository.getById(id);
    }

    public User findByEmail(String email){
        return userRepository.findByEmail(email);
    }

    public User update(User user){
        User update = userRepository.getById(user.getId());
        if(update != null) {
            userRepository.save(user);
            return update;
        }
        return user;
    }

    public User deleteById(Long id){
        User user = userRepository.getById(id);
        if (user != null) {
            userRepository.deleteById(id);
            return user;
        }
        return user;
    }
}
