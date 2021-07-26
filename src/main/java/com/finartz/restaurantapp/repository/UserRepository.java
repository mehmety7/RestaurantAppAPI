package com.finartz.restaurantapp.repository;

import com.finartz.restaurantapp.model.User;

public interface UserRepository extends BaseRepository<User> {

    User findByEmail(String Email);

}
