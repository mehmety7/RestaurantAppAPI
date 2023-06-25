package com.finartz.restaurantapp.repository;

import com.finartz.restaurantapp.model.entity.UserEntity;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles("test")
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void whenFindByEmail_thenReturnUserEntities(){
        UserEntity userEntity = userRepository.findByEmail("ali@gmail.com");

        Assertions.assertEquals(userEntity.getEmail(), "ali@gmail.com");
    }

}
