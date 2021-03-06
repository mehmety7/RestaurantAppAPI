package com.finartz.restaurantapp.repository;

import com.finartz.restaurantapp.model.entity.RestaurantEntity;
import com.finartz.restaurantapp.model.enumerated.RestaurantStatus;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@TestPropertySource("classpath:application-test.properties")
@DataJpaTest
public class RestaurantRepositoryTest {

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Test
    public void whenFindByStatus_thenReturnRestaurantEntities(){
        List<RestaurantEntity> restaurantEntities = restaurantRepository.findByRestaurantStatus(RestaurantStatus.WAITING);

        Assertions.assertEquals(restaurantEntities.get(0).getRestaurantStatus(), RestaurantStatus.WAITING);
    }

}
