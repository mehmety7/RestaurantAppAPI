package com.finartz.restaurantapp.repository;

import com.finartz.restaurantapp.model.entity.MenuEntity;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@DataJpaTest
public class MenuRepositoryTest {

    @Autowired
    private MenuRepository menuRepository;

    @Test
    public void whenGetMenuEntityByBranchEntityId_thenReturnMenuEntity(){
        MenuEntity menuEntity = menuRepository.getMenuEntityByBranchEntityId(1L);

        Assertions.assertEquals(1L, menuEntity.getBranchEntity().getId());
    }

}