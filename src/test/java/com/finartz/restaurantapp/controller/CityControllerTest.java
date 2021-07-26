package com.finartz.restaurantapp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.finartz.restaurantapp.model.City;
import com.finartz.restaurantapp.service.CityService;
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

@RunWith(SpringRunner.class)
@ExtendWith(SpringExtension.class)
@WebMvcTest(CityController.class)
public class CityControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CityService cityService;

    @Test
    public void whenGetAllCity_thenReturnCity() throws Exception {

        City city = City.builder()
                .id(1L)
                .name("Adana")
                .build();

        List<City> cityList = Arrays.asList(city);

        Mockito.when(cityService.getAll()).thenReturn(cityList);

        mockMvc.perform(get("/city")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(1)))
                .andExpect(jsonPath("$[0].name", Matchers.is("Adana")));

    }

    @Test
    public void whenGetCityById_thenReturnCity() throws Exception {

        City city = City.builder()
                .id(1L)
                .name("Adana")
                .build();

        Mockito.when(cityService.getById(1L)).thenReturn(city);

        mockMvc.perform(get("/city/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("name", Matchers.is("Adana")));

    }

    @Test
    public void whenCreateNewCity_thenReturnCity() throws Exception {

        City city = City.builder()
                .id(1L)
                .name("Adana")
                .build();

        Mockito.when(cityService.create(city)).thenReturn(city);

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = ow.writeValueAsString(city);

        mockMvc.perform(post("/city")
                .contentType(MediaType.APPLICATION_JSON).content(requestJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("name", Matchers.is("Adana")));
    }

    @Test
    public void whenUpdateCity_thenReturnCity() throws Exception {

        City city = City.builder()
                .id(1L)
                .name("Adana")
                .build();

        city.setName("ADANA");

        Mockito.when(cityService.update(city)).thenReturn(city);

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = ow.writeValueAsString(city);

        mockMvc.perform(put("/city")
                .contentType(MediaType.APPLICATION_JSON).content(requestJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("name", Matchers.is("ADANA")));
    }

    @Test
    public void whenDeleteCity_thenReturnCity() throws Exception {

        City city = City.builder()
                .id(1L)
                .name("Adana")
                .build();

        Mockito.when(cityService.deleteById(1L)).thenReturn(city);

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = ow.writeValueAsString(city);

        mockMvc.perform(delete("/city/1")
                .contentType(MediaType.APPLICATION_JSON).content(requestJson))
                .andExpect(status().isOk());
    }


}
