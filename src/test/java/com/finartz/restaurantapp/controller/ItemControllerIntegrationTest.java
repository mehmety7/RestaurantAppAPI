package com.finartz.restaurantapp.controller;

import com.finartz.restaurantapp.model.dto.ItemDto;
import com.finartz.restaurantapp.model.dto.PageDto;
import com.finartz.restaurantapp.model.request.create.ItemCreateRequest;
import com.finartz.restaurantapp.model.request.get.ItemPageGetRequest;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(properties = "spring.main.banner-mode=off")
@TestPropertySource("classpath:application-test.properties")
public class ItemControllerIntegrationTest {

    @Autowired
    private ItemController itemController;

    @Test
    public void whenGetAll_thenReturnAllItems(){
        ItemPageGetRequest itemPageGetRequest = ItemPageGetRequest.builder().pageNo(0).pageSize(2).sortBy("id").build();
        ResponseEntity<PageDto<ItemDto>> response = itemController.getItems(itemPageGetRequest);

        Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);
        Assertions.assertEquals(response.getBody().getResponse().get(0).getId() , 1l);
    }

    @Test
    public void whenGetById_thenReturnItem(){
        ResponseEntity<ItemDto> response = itemController.getItem(1l);

        Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);
        Assertions.assertEquals(response.getBody().getId(), 1l);
    }

    @Test
    public void whenCreateNewItem_thenReturnItem(){
        ItemCreateRequest itemCreateRequest = ItemCreateRequest
                .builder()
                .name("Item")
                .unitType("unit")
                .build();

        ResponseEntity<ItemDto> response = itemController.createItem(itemCreateRequest);

        Assertions.assertEquals(response.getStatusCode(), HttpStatus.CREATED);
        Assertions.assertEquals(response.getBody().getName(), "Item");

    }



}
