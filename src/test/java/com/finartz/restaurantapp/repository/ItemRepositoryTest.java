package com.finartz.restaurantapp.repository;

import com.finartz.restaurantapp.model.entity.ItemEntity;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@DataJpaTest
@RunWith(SpringRunner.class)
@ActiveProfiles("test")
public class ItemRepositoryTest {

    @Autowired
    private ItemRepository itemRepository;

    @Test
    public void whenCountItemEntitiesBy(){
        Integer itemCount = itemRepository.countItemEntitiesBy();
        List<ItemEntity> itemEntities = itemRepository.findAll();

        Assertions.assertEquals(itemCount, itemEntities.size());

    }


}
