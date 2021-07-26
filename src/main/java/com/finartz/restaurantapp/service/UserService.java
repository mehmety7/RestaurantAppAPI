package com.finartz.restaurantapp.service;

import com.finartz.restaurantapp.model.User;
import com.finartz.restaurantapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User create(User user){
        User save = userRepository.save(user);
        return save;
    }

    public List<User> getAll(){
        List<User> users = userRepository.findAll();
        return users;
    }

    public User getById(Long id){
        User user = userRepository.getById(id);
        return user;
    }

    public User getByEmail(String email){
        User user = userRepository.findByEmail(email);
        return user;
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
