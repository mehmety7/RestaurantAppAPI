package com.finartz.restaurantapp.controller;

import com.finartz.restaurantapp.model.dto.CityDto;
import com.finartz.restaurantapp.service.CityService;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@ExtendWith(SpringExtension.class)
@WebMvcTest(CityController.class)
@AutoConfigureMockMvc(addFilters = false)
public class CityControllerTest {

    private static final String URI_CITY = "/city";
    private static final String COUNTY_ADANA = "Adana";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CityService cityService;

    @MockBean
    private UserDetailsService userDetailsService;

    @Test
    public void whenGetAllCity_thenReturnCity() throws Exception {

        CityDto city = CityDto.builder()
                .id(1L)
                .name(COUNTY_ADANA)
                .build();

        List<CityDto> cityList = Arrays.asList(city);

        Mockito.when(cityService.getCities()).thenReturn(cityList);

        mockMvc.perform(get(URI_CITY)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(1)))
                .andExpect(jsonPath("$[0].name", Matchers.is(COUNTY_ADANA)));

    }

    @Test
    public void whenGetCityById_thenReturnCities() throws Exception {

        CityDto city = CityDto.builder()
                .id(1L)
                .name(COUNTY_ADANA)
                .build();

        Mockito.when(cityService.getCity(1L)).thenReturn(city);

        mockMvc.perform(get(URI_CITY + "/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("name", Matchers.is(COUNTY_ADANA)));

    }


}
