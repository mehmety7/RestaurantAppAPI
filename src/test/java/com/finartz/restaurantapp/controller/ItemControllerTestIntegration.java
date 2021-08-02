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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest(properties = "spring.main.banner-mode=off")
@Transactional
public class ItemControllerTestIntegration {

    @Autowired
    private ItemController itemController;

    @Autowired
    private ItemService itemService;

    @Autowired
    private ItemRepository itemRepository;


    @Before
    public void setUp() {
        itemRepository.deleteAll();
        itemService.create(TestData.createItems().get(0));
        itemService.create(TestData.createItems().get(1));
    }

    @Test
    public void testGetAll() {
        ResponseEntity<List<Item>> response = itemController.getAll();
        Assert.assertEquals(2, response.getBody().size());
        System.out.println(response.getBody());
    }

    @Test
    public void testGetById() {
        ResponseEntity<Item> response = itemController.get(1L);
        Assert.assertEquals("Hamburger", response.getBody().getName());
        System.out.println(response.getBody());
    }

    @Test
    public void testCreateNewItem() {
        Item item = new Item(3L, "Pizza", "piece");
        ResponseEntity<Item> response = itemController.create(item);
        Assert.assertEquals(response.getBody(), item);
        Assert.assertEquals(response.getStatusCode(), HttpStatus.CREATED);
    }

    @Test
    public void testUpdateItem() {
        ResponseEntity<List<Item>> response = itemController.getAll();
        System.out.println(response.getBody().get(0).getName());
        response.getBody().get(0).setName("Kebab");
        ResponseEntity<Item> response2 = itemController.update(response.getBody().get(0));
        System.out.println(response2.getBody().getName());
        Assert.assertEquals(response2.getBody().getName(), "Kebab");
        Assert.assertEquals(response2.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void testDeleteItem() {
        System.out.println(itemController.get(1L).getBody().getName());
        ResponseEntity<Item> response = itemController.deleteById(1L);
        Assert.assertNull(response.getBody());
        Assert.assertEquals(response.getStatusCode(), HttpStatus.OK);
    }

}