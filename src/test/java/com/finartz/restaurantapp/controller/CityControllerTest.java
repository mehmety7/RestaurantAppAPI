package com.finartz.restaurantapp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.finartz.restaurantapp.model.City;
import com.finartz.restaurantapp.service.CityService;
import org.hamcrest.Matchers;
import org.junit.Before;
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
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@ExtendWith(SpringExtension.class)
@WebMvcTest(CityController.class)
public class CityControllerTest {

    private static final String URI_CITY = "/city";
    private static final String COUNTY_ADANA = "Adana";
    private static final String COUNTY_HATAY = "Hatay";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CityService cityService;

    @MockBean
    private UserDetailsService userDetailsService;

    private ObjectWriter objectWriter;

    @Before
    public void init() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        objectWriter = mapper.writer().withDefaultPrettyPrinter();
    }

    @Test
    public void whenGetAllCity_thenReturnCity() throws Exception {

        City city = City.builder()
                .id(1L)
                .name(COUNTY_ADANA)
                .build();

        List<City> cityList = Arrays.asList(city);

        Mockito.when(cityService.getAll()).thenReturn(cityList);

        mockMvc.perform(get(URI_CITY)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(1)))
                .andExpect(jsonPath("$[0].name", Matchers.is(COUNTY_ADANA)));

    }

    @Test
    public void whenGetCityById_thenReturnCity() throws Exception {

        City city = City.builder()
                .id(1L)
                .name(COUNTY_ADANA)
                .build();

        Mockito.when(cityService.getById(1L)).thenReturn(city);

        mockMvc.perform(get(URI_CITY + "/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("name", Matchers.is(COUNTY_ADANA)));

    }

    @Test
    public void whenCreateNewCity_thenReturnCity() throws Exception {

        City city = City.builder()
                .id(1L)
                .name(COUNTY_ADANA)
                .build();

        Mockito.when(cityService.create(city)).thenReturn(city);

        String requestJson = objectWriter.writeValueAsString(city);

        mockMvc.perform(post(URI_CITY)
                .contentType(MediaType.APPLICATION_JSON).content(requestJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("name", Matchers.is(COUNTY_ADANA)));
    }

    @Test
    public void whenUpdateCity_thenReturnCity() throws Exception {

        City city = City.builder()
                .id(1L)
                .name(COUNTY_ADANA)
                .build();

        City modifyCity = City.builder().id(1l).name(COUNTY_HATAY).build();

        Mockito.when(cityService.create(city)).thenReturn(city);
        Mockito.when(cityService.update(modifyCity)).thenReturn(modifyCity);

        String requestJson1 = objectWriter.writeValueAsString(city);
        String requestJson2 = objectWriter.writeValueAsString(modifyCity);

        mockMvc.perform(post(URI_CITY)
                .contentType(MediaType.APPLICATION_JSON).content(requestJson1))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("name", Matchers.is(COUNTY_ADANA)))
                .andExpect(jsonPath("id", Matchers.is(1)));

        mockMvc.perform(put(URI_CITY)
                .contentType(MediaType.APPLICATION_JSON).content(requestJson2))
                .andExpect(status().isOk())
                .andExpect(jsonPath("name", Matchers.is(COUNTY_HATAY)))
                .andExpect(jsonPath("id", Matchers.is(1)));
    }

    @Test
    public void whenDeleteCity_thenReturnCity() throws Exception {

        City city = City.builder()
                .id(1L)
                .name(COUNTY_ADANA)
                .build();

        Mockito.when(cityService.deleteById(1L)).thenReturn(city);

        mockMvc.perform(delete(URI_CITY + "/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }


}
