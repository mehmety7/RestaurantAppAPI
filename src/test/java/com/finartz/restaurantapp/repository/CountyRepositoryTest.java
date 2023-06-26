package com.finartz.restaurantapp.repository;

import com.finartz.restaurantapp.model.entity.CountyEntity;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@DataJpaTest
public class CountyRepositoryTest {

    @Autowired
    private CountyRepository countyRepository;

    @Test
    public void whenGetCountyEntitiesByCityEntityId_thenReturnCountyEntities(){
        List<CountyEntity> countyEntities = countyRepository.getCountyEntitiesByCityEntityId(34L);

        Assertions.assertEquals(34L, countyEntities.get(0).getCityEntity().getId());
    }

}
