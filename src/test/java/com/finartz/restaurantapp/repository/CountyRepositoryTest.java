package com.finartz.restaurantapp.repository;

import com.finartz.restaurantapp.model.entity.CountyEntity;
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
public class CountyRepositoryTest {

    @Autowired
    private CountyRepository countyRepository;

    @Test
    public void whenGetCountyEntitiesByCityEntity_Id_thenReturnCountyEntities(){
        List<CountyEntity> countyEntities = countyRepository.getCountyEntitiesByCityEntity_Id(34l);

        Assertions.assertEquals(countyEntities.get(0).getCityEntity().getId(), 34l);
    }

}
