package com.finartz.restaurantapp.repository;

import com.finartz.restaurantapp.model.entity.BranchEntity;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.stream.Collectors;

@RunWith(SpringRunner.class)
@TestPropertySource("classpath:application-test.properties")
@DataJpaTest
public class BranchRepositoryTest {

    @Autowired
    private BranchRepository branchRepository;

    @Test
    public void whenGetBranchEntitiesByAddressEntity_CountyEntity_Id(){
        Pageable pageable = PageRequest.of(0, 1, Sort.by("id"));
        Page<BranchEntity> branchEntityPage = branchRepository.getBranchEntitiesByAddressEntity_CountyEntity_Id(896l, pageable);

        List<BranchEntity> branchEntities = branchEntityPage.get().collect(Collectors.toList());

        Assertions.assertEquals(branchEntities.get(0).getAddressEntity().getCountyEntity().getId(), 896l);
    }

    @Test
    public void whenCountBranchEntitiesByAddressEntity_CountyEntity_Id(){
        Integer count = branchRepository.countBranchEntitiesByAddressEntity_CountyEntity_Id(896l);

        Assertions.assertEquals(count, 1);
    }

    @Test
    public void whenGetEntityOwnerUserIdByRestaurantId(){
        Long entityOwnerId = branchRepository.getEntityOwnerUserIdByRestaurantId(1l);

        Assertions.assertEquals(entityOwnerId, 2L);

    }

}
