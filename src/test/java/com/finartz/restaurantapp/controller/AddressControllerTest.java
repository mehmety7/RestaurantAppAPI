package com.finartz.restaurantapp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.finartz.restaurantapp.model.dto.AddressDto;
import com.finartz.restaurantapp.model.request.create.AddressCreateRequest;
import com.finartz.restaurantapp.service.AddressService;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@RunWith(SpringRunner.class)
@WebMvcTest(AddressController.class)
public class AddressControllerTest {

    private static final String URI_ADDRESS = "/address";
    private static final String DISTRICT_MERKEZ = "Merkez";
    private static final String NAME_EV = "Ev";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AddressService addressService;

    @MockBean
    private UserDetailsService userDetailsService;

    @Test
    public void whenGetByAddressId_thenReturnAddress() throws Exception {

        AddressDto address = AddressDto.builder().name(NAME_EV).district(DISTRICT_MERKEZ).id(1L).build();

        Mockito.when(addressService.getAddress(1L)).thenReturn(address);

        mockMvc.perform(get(URI_ADDRESS + "/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("name", Matchers.is(NAME_EV)));

    }

    @Test
    public void whenGetByUserId_thenReturnUserAddresses() throws Exception {

        AddressDto address = AddressDto.builder().name(NAME_EV).district(DISTRICT_MERKEZ).id(1L).build();

        Mockito.when(addressService.getUserAddresses(1L)).thenReturn(Arrays.asList(address));

        mockMvc.perform(get(URI_ADDRESS + "/user?user_id=1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name", Matchers.is(NAME_EV)));

    }

    @Test
    public void whenGetByBranchId_thenReturnBranchAddress() throws Exception {

        AddressDto address = AddressDto.builder().name(NAME_EV).district(DISTRICT_MERKEZ).id(1L).build();

        Mockito.when(addressService.getBranchAddress(1L)).thenReturn(address);

        mockMvc.perform(get(URI_ADDRESS + "/branch?branch_id=1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("name", Matchers.is(NAME_EV)));

    }

    @Test
    public void whenCreateNewAddress_thenReturnCreated() throws Exception {

        AddressDto address = AddressDto
                .builder()
                .id(1L)
                .build();

        AddressCreateRequest addressCreateRequest = AddressCreateRequest
                .builder()
                .name(NAME_EV)
                .district(DISTRICT_MERKEZ)
                .cityId(1l)
                .countyId(1l)
                .branchId(null)
                .otherContent(anyString())
                .userId(1l)
                .build();

        Mockito.when(addressService.createAddress(addressCreateRequest)).thenReturn(address);

        // to-do : https://stackoverflow.com/questions/20504399/testing-springs-requestbody-using-spring-mockmvc
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter objectWriter = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = objectWriter.writeValueAsString(addressCreateRequest);

        mockMvc.perform(post(URI_ADDRESS)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(requestJson))
                .andExpect(status().isCreated());
    }

    @Test
    public void whenSetActiveAddress_thenReturnOk() throws Exception {

        mockMvc.perform(put(URI_ADDRESS + "/" + anyLong())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }


}
