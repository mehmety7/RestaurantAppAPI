package com.finartz.restaurantapp.controller;

import com.finartz.restaurantapp.model.dto.AddressDto;
import com.finartz.restaurantapp.model.request.create.AddressCreateRequest;
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

import java.util.List;
import java.util.Objects;

import static org.mockito.ArgumentMatchers.anyLong;

@RunWith(SpringRunner.class)
@SpringBootTest(properties = "spring.main.banner-mode=off")
@ActiveProfiles("test")
public class AddressControllerIntegrationTest {

    @Autowired
    private AddressController addressController;

    @MockBean
    private TokenService tokenService;

    @BeforeEach
    public void init(){
        Mockito.doNothing().when(tokenService).checkRequestOwnerAuthoritative(anyLong());
    }

    @Test
    public void whenGetByValidAddressId_thenReturnAddress() {
        ResponseEntity<AddressDto> response = addressController.getAddress(1L);

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(1L, Objects.requireNonNull(response.getBody()).getId());
    }

    @Test
    public void whenGetByValidUserId_thenReturnUserAddresses() {
        ResponseEntity<List<AddressDto>> response = addressController.getUserAddresses(3L);

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(3L, Objects.requireNonNull(response.getBody()).get(0).getUserId());
    }

    @Test
    public void whenGetByValidBranchId_thenReturnBranchAddress() {
        ResponseEntity<AddressDto> response = addressController.getBranchAddress(1L);

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(1L, Objects.requireNonNull(response.getBody()).getBranchId());
    }

    @Test
    public void givenValidAddressCreateRequest_whenCreateNewAddress_thenReturnCreated() {
        AddressCreateRequest addressCreateRequest = AddressCreateRequest
                .builder()
                .name("Address").cityId(34L).countyId(116L).district("District").otherContent("Other Info")
                .userId(3L).branchId(null)
                .build();

        ResponseEntity<AddressDto> response = addressController.createAddress(addressCreateRequest);

        Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());
        Assertions.assertEquals(3L, Objects.requireNonNull(response.getBody()).getUserId());
    }

    @Test
    public void whenSetActiveAddress_thenReturnOk() {
        // Change the active address from address id 5l to address id 2L for user who has user id 3
        ResponseEntity<AddressDto> response = addressController.setActiveAddress(2L);

        ResponseEntity<AddressDto> addressId2 = addressController.getAddress(2L);
        ResponseEntity<AddressDto> addressId5 = addressController.getAddress(5L);

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(true, Objects.requireNonNull(addressId2.getBody()).getEnable());
        Assertions.assertEquals(false, Objects.requireNonNull(addressId5.getBody()).getEnable());
    }



}
