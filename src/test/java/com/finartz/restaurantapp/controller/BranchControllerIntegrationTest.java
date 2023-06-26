package com.finartz.restaurantapp.controller;

import com.finartz.restaurantapp.model.dto.BranchDto;
import com.finartz.restaurantapp.model.dto.PageDto;
import com.finartz.restaurantapp.model.request.create.BranchCreateRequest;
import com.finartz.restaurantapp.model.request.get.BranchPageGetRequest;
import com.finartz.restaurantapp.service.TokenService;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Objects;

import static org.mockito.ArgumentMatchers.anyLong;

@RunWith(SpringRunner.class)
@SpringBootTest(properties = "spring.main.banner-mode=off")
@ActiveProfiles("test")
public class BranchControllerIntegrationTest {

    @Autowired
    private BranchController branchController;

    @MockBean
    private TokenService tokenService;

    @BeforeEach
    public void init(){
        Mockito.doNothing().when(tokenService).checkRequestOwnerAuthoritative(anyLong());
    }

    @Test
    public void whenGetByBranchId_thenReturnBranch() {
        ResponseEntity<BranchDto> response = branchController.getBranch(1L);

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(1L, Objects.requireNonNull(response.getBody()).getId());
    }

    @Test
    public void whenGetByCountyId_thenReturnBranches() {
        BranchPageGetRequest branchPageGetRequest = BranchPageGetRequest.builder().pageNo(0).pageSize(1).sortBy("id").countyId(855L).build();
        ResponseEntity<PageDto<BranchDto>> response = branchController.getBranches(branchPageGetRequest);

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());

//      Reminder :
//          INSERT INTO addresses (id, branch_id, user_id, name, city_id, county_id, district, other_content, enable)
//          VALUES                (3, 1, NULL, 'Kral Burger Şile', 34, 855, 'Ağva', '100. Sokak No 1', true);

        Assertions.assertEquals("Kral Burger Şile", Objects.requireNonNull(response.getBody()).getResponse().get(0).getName());
    }

    @Test
    public void whenCreateNewBranch_thenReturnCreated() {
        BranchCreateRequest branchCreateRequest = BranchCreateRequest
                .builder()
                .name("Branch")
                .restaurantId(1L)
                .addressCreateRequest(null)
                .build();

        ResponseEntity<BranchDto> response = branchController.createBranch(branchCreateRequest);

        Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }



}
