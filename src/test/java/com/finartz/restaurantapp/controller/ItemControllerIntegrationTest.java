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
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Objects;

@RunWith(SpringRunner.class)
@SpringBootTest(properties = "spring.main.banner-mode=off")
@ActiveProfiles("test")
public class ItemControllerIntegrationTest {

    public static final String ITEM = "Item";
    public static final String UNIT = "unit";
    public static final String ID = "id";
    @Autowired
    private ItemController itemController;

    @Test
    public void whenGetAll_thenReturnAllItems(){
        ItemPageGetRequest itemPageGetRequest = ItemPageGetRequest.builder().pageNo(0).pageSize(2).sortBy(ID).build();
        ResponseEntity<PageDto<ItemDto>> response = itemController.getItems(itemPageGetRequest);

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(1L , Objects.requireNonNull(response.getBody()).getResponse().get(0).getId());
    }

    @Test
    public void whenGetById_thenReturnItem(){
        ResponseEntity<ItemDto> response = itemController.getItem(1L);

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(1L, Objects.requireNonNull(response.getBody()).getId());
    }

    @Test
    public void whenCreateNewItem_thenReturnItem(){
        ItemCreateRequest itemCreateRequest = ItemCreateRequest
                .builder()
                .name(ITEM)
                .unitType(UNIT)
                .build();

        ResponseEntity<ItemDto> response = itemController.createItem(itemCreateRequest);

        Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());
        Assertions.assertEquals(ITEM, Objects.requireNonNull(response.getBody()).getName());

    }



}
