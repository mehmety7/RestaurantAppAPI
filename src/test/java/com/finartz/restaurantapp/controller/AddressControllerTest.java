package com.finartz.restaurantapp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.finartz.restaurantapp.model.Address;
import com.finartz.restaurantapp.model.City;
import com.finartz.restaurantapp.model.County;
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
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@RunWith(SpringRunner.class)
@WebMvcTest(AddressController.class)
public class AddressControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AddressService addressService;

    @Test
    public void whenGetAllAddress_thenReturnAddress() throws Exception {

        City city = City.builder().name("İstanbul").build();

        County county = County.builder().name("Avcılar").city(city).build();

        Address address = Address.builder().county(county).city(city).name("Ev").district("Merkez Mahallesi").build();

        List<Address> addressList = Arrays.asList(address);

        Mockito.when(addressService.getAll()).thenReturn(addressList);

        mockMvc.perform(get("/address")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(1)))
                .andExpect(jsonPath("$[0].name", Matchers.is("Ev")));
    }

    @Test
    public void whenGetByAddressId_thenReturnAddress() throws Exception {

        City city = City.builder().name("İstanbul").build();

        County county = County.builder().name("Avcılar").city(city).build();

        Address address = Address.builder().county(county).city(city).name("Ev").district("Merkez Mahallesi").id(1L).build();

        Mockito.when(addressService.getById(1L)).thenReturn(address);

        mockMvc.perform(get("/address/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("name", Matchers.is("Ev")));

    }

    @Test
    public void whenCreateNewAddress_thenReturnCreated() throws Exception {

        City city = City.builder().name("İstanbul").build();

        County county = County.builder().name("Avcılar").city(city).build();

        Address address = Address.builder().county(county).city(city).name("Ev").district("Merkez Mahallesi").id(1L).build();

        Mockito.when(addressService.create(address)).thenReturn(address);

        // to-do : https://stackoverflow.com/questions/20504399/testing-springs-requestbody-using-spring-mockmvc
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = ow.writeValueAsString(address);

        mockMvc.perform(post("/address")
                .contentType(MediaType.APPLICATION_JSON).content(requestJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("name", Matchers.is("Ev")));

    }

    @Test
    public void whenUpdateExistsAddress_thenReturnUpdated() throws Exception {

        City city = City.builder().name("İstanbul").build();

        County county = County.builder().name("Avcılar").city(city).build();

        Address address = Address.builder().county(county).city(city).name("Ev").district("Merkez Mahallesi").id(1L).build();

        address.setDistrict("Üniversite Mahallesi");

        Mockito.when(addressService.update(address)).thenReturn(address);

        // to-do : https://stackoverflow.com/questions/20504399/testing-springs-requestbody-using-spring-mockmvc
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = ow.writeValueAsString(address);

        mockMvc.perform(put("/address")
                .contentType(MediaType.APPLICATION_JSON).content(requestJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("district", Matchers.is("Üniversite Mahallesi")));

    }

    @Test
    public void whenDeleteExistsAddress_thenReturnOk() throws Exception {

        City city = City.builder().name("İstanbul").build();

        County county = County.builder().name("Avcılar").city(city).build();

        Address address = Address.builder().county(county).city(city).name("Ev").district("Merkez Mahallesi").id(1L).build();

        Mockito.when(addressService.deleteById(1L)).thenReturn(address);

        mockMvc.perform(delete("/address/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

}
