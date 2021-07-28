package com.finartz.restaurantapp.repository;

import com.finartz.restaurantapp.model.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends BaseRepository<User> {

    User findByEmail(String Email);

}
