package com.finartz.restaurantapp.controller;

import com.finartz.restaurantapp.model.dto.MenuDto;
import com.finartz.restaurantapp.model.request.create.MenuCreateRequest;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@RunWith(SpringRunner.class)
@SpringBootTest(properties = "spring.main.banner-mode=off")
@ActiveProfiles("test")
public class MenuControllerIntegrationTest {

    @Autowired
    private MenuController menuController;

    @Test
    public void whenGetMenuById_thenReturnMenu() {
        ResponseEntity<MenuDto> response = menuController.getMenu(1L);

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(1L, Objects.requireNonNull(response.getBody()).getId());
    }

    @Test
    public void whenGetMenuByBranchId_thenReturnBranchMenu() {
        ResponseEntity<MenuDto> response = menuController.getBranchMenu(1L);

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());

//      Reminder : INSERT INTO menus (id, branch_id) values (1, 1);
        Assertions.assertEquals(1L, Objects.requireNonNull(response.getBody()).getId());
    }

    @Test
    @Transactional
    public void whenCreateNewMenu_thenReturnMenu() {
        MenuCreateRequest menuCreateRequest = MenuCreateRequest
                .builder()
                .branchId(2L)
                .build();

        ResponseEntity<MenuDto> response = menuController.createMenu(menuCreateRequest);

        Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

}
