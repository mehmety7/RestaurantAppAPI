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
    public void whenGetMenuEntityByBranchEntity_Id_thenReturnMenuEntity(){
        MenuEntity menuEntity = menuRepository.getMenuEntityByBranchEntity_Id(1l);

        Assertions.assertEquals(menuEntity.getBranchEntity().getId(), 1l);
    }

}