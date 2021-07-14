package com.finartz.restaurantapp.controller;

import com.finartz.restaurantapp.data.TestData;
import com.finartz.restaurantapp.model.Item;
import com.finartz.restaurantapp.repository.ItemRepository;
import com.finartz.restaurantapp.service.ItemService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest(properties = "spring.main.banner-mode=off")
@Transactional
public class ItemControllerTest {

    @Autowired
    private ItemController itemController;

    @Autowired
    private ItemService itemService;

    @Autowired
    private ItemRepository itemRepository;


    @Before
    public void init() {
        itemRepository.deleteAll();
        itemService.create(TestData.createItems().get(0));
        itemService.create(TestData.createItems().get(1));
    }

    @Test
    public void findAll_Success() {
        ResponseEntity<List<Item>> response = itemController.getAll();
        Assert.assertEquals(2, response.getBody().size());
    }

}