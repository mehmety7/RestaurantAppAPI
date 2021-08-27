package com.finartz.restaurantapp.repository;

import com.finartz.restaurantapp.model.entity.BranchEntity;
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
public class BranchRepositoryTest {

    @Autowired
    private BranchRepository branchRepository;

    @Test
    public void whenGetBranchEntitiesByAddressEntity_CountyEntity_Id(){
        List<BranchEntity> branchEntities = branchRepository.getBranchEntitiesByAddressEntity_CountyEntity_Id(896l);

        Assertions.assertEquals(branchEntities.get(0).getAddressEntity().getCountyEntity().getId(), 896l);
    }
}
