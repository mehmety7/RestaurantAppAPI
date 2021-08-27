package com.finartz.restaurantapp.controller;

import com.finartz.restaurantapp.model.dto.AddressDto;
import com.finartz.restaurantapp.model.request.create.AddressCreateRequest;
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
public class AddressControllerIntegrationTest {

    @Autowired
    private AddressController addressController;

    @Test
    public void whenGetByValidAddressId_thenReturnAddress() {
        ResponseEntity<AddressDto> response = addressController.getAddress(1l);

        Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);
        Assertions.assertEquals(response.getBody().getId(), 1l);
    }

    @Test
    public void whenGetByValidUserId_thenReturnUserAddresses() {
        ResponseEntity<List<AddressDto>> response = addressController.getUserAddresses(3l);

        Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);
        Assertions.assertEquals(response.getBody().get(0).getUserId(), 3l);
    }

    @Test
    public void whenGetByValidBranchId_thenReturnBranchAddress() {
        ResponseEntity<AddressDto> response = addressController.getBranchAddress(1l);

        Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);
        Assertions.assertEquals(response.getBody().getBranchId(), 1l);
    }

    @Test
    public void givenValidAddressCreateRequest_whenCreateNewAddress_thenReturnCreated() {
        AddressCreateRequest addressCreateRequest = AddressCreateRequest
                .builder()
                .name("Address").cityId(34l).countyId(116l).district("District").otherContent("Other Info")
                .userId(3l).branchId(null)
                .build();

        ResponseEntity<AddressDto> response = addressController.createAddress(addressCreateRequest);

        Assertions.assertEquals(response.getStatusCode(), HttpStatus.CREATED);
        Assertions.assertEquals(response.getBody().getUserId(), 3l);
    }

    @Test
    public void whenSetActiveAddress_thenReturnOk() {
        // Change the active address from address id 5l to address id 2l for user who has user id 3
        ResponseEntity<AddressDto> response = addressController.setActiveAddress(2l);

        ResponseEntity<AddressDto> addressId2 = addressController.getAddress(2l);
        ResponseEntity<AddressDto> addressId5 = addressController.getAddress(5l);

        Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);
        Assertions.assertEquals(addressId2.getBody().getEnable(), true);
        Assertions.assertEquals(addressId5.getBody().getEnable(), false);
    }



}
