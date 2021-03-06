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
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest(properties = "spring.main.banner-mode=off")
@TestPropertySource("classpath:application-test.properties")
public class MenuControllerIntegrationTest {

    @Autowired
    private MenuController menuController;

    @Test
    public void whenGetMenuById_thenReturnMenu() {
        ResponseEntity<MenuDto> response = menuController.getMenu(1l);

        Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);
        Assertions.assertEquals(response.getBody().getId(), 1l);
    }

    @Test
    public void whenGetMenuByBranchId_thenReturnBrancheMenu() {
        ResponseEntity<MenuDto> response = menuController.getBranchMenu(1l);

        Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);

//      Reminder : INSERT INTO menus (id, branch_id) values (1, 1);
        Assertions.assertEquals(response.getBody().getId(), 1l);
    }

    @Test
    @Transactional
    public void whenCreateNewMenu_thenReturnMenu() {
        MenuCreateRequest menuCreateRequest = MenuCreateRequest
                .builder()
                .branchId(2l)
                .build();

        ResponseEntity<MenuDto> response = menuController.createMenu(menuCreateRequest);

        Assertions.assertEquals(response.getStatusCode(), HttpStatus.CREATED);
    }

}
