package com.finartz.restaurantapp.repository;

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
public class MealRepositoryTest {

    @Autowired
    private MealRepository mealRepository;

    @Test
    public void whenGetEntityOwnerUserIdByMenuId(){
        Long entityOwnerId = mealRepository.getEntityOwnerUserIdByMenuId(1L);

        Assertions.assertEquals(2L, entityOwnerId);
    }

}
