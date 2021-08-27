package com.finartz.restaurantapp.controller;

import com.finartz.restaurantapp.model.dto.BranchDto;
import com.finartz.restaurantapp.model.request.create.BranchCreateRequest;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(properties = "spring.main.banner-mode=off")
@TestPropertySource("classpath:application-test.properties")
public class BranchControllerIntegrationTest {

    @Autowired
    private BranchController branchController;

    @Test
    public void whenGetByBranchId_thenReturnBranch() {
        ResponseEntity<BranchDto> response = branchController.getBranch(1l);

        Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);
        Assertions.assertEquals(response.getBody().getId(), 1l);
    }

    @Test
    public void whenGetByCountyId_thenReturnBranches() {
        ResponseEntity<List<BranchDto>> response = branchController.getBranches(855l);

        Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);

//      Reminder :
//          INSERT INTO addresses (id, branch_id, user_id, name, city_id, county_id, district, other_content, enable)
//          VALUES (3, 1, NULL, 'Kral Burger Şile', 34, 855, 'Ağva', '100. Sokak No 1', true);
        Assertions.assertEquals(response.getBody().get(0).getName(), "Kral Burger Şile");
    }

    @Test
    public void whenCreateNewBranch_thenReturnCreated() {
        BranchCreateRequest branchCreateRequest = BranchCreateRequest
                .builder()
                .name("Branch")
                .restaurantId(1l)
                .addressCreateRequest(null)
                .build();

        ResponseEntity<BranchDto> response = branchController.createBranch(branchCreateRequest);

        Assertions.assertEquals(response.getStatusCode(), HttpStatus.CREATED);
    }



}
