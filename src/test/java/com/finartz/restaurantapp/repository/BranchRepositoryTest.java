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
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.stream.Collectors;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@DataJpaTest
public class BranchRepositoryTest {

    @Autowired
    private BranchRepository branchRepository;

    @Test
    public void whenGetBranchEntitiesByAddressEntity_CountyEntityId(){
        Pageable pageable = PageRequest.of(0, 1, Sort.by("id"));
        Page<BranchEntity> branchEntityPage = branchRepository.getBranchEntitiesByAddressEntityCountyEntityId(896L, pageable);

        List<BranchEntity> branchEntities = branchEntityPage.get().collect(Collectors.toList());

        Assertions.assertEquals(896L, branchEntities.get(0).getAddressEntity().getCountyEntity().getId());
    }

    @Test
    public void whenCountBranchEntitiesByAddressEntity_CountyEntityId(){
        Integer count = branchRepository.countBranchEntitiesByAddressEntityCountyEntityId(896L);

        Assertions.assertEquals(1, count);
    }

    @Test
    public void whenGetEntityOwnerUserIdByRestaurantId(){
        Long entityOwnerId = branchRepository.getEntityOwnerUserIdByRestaurantId(1L);

        Assertions.assertEquals(2L, entityOwnerId);

    }

}
