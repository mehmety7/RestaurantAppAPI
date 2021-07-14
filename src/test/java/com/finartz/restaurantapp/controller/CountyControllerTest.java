package com.finartz.restaurantapp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.finartz.restaurantapp.model.County;
import com.finartz.restaurantapp.service.CountyService;
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
@WebMvcTest(CountyController.class)
public class CountyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CountyService countyService;

    @Test
    public void whenGetAllCounty_thenReturnCounty() throws Exception {

        County county = County.builder()
                .id(1L)
                .name("Adana")
                .build();

        List<County> countyList = Arrays.asList(county);

        Mockito.when(countyService.getAll()).thenReturn(countyList);

        mockMvc.perform(get("/county")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(1)))
                .andExpect(jsonPath("$[0].name", Matchers.is("Adana")));

    }

    @Test
    public void whenGetCountyById_thenReturnCounty() throws Exception {

        County county = County.builder()
                .id(1L)
                .name("Adana")
                .build();

        Mockito.when(countyService.getById(1L)).thenReturn(county);

        mockMvc.perform(get("/county/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("name", Matchers.is("Adana")));

    }

    @Test
    public void whenCreateNewCounty_thenReturnCounty() throws Exception {

        County county = County.builder()
                .id(1L)
                .name("Adana")
                .build();

        Mockito.when(countyService.create(county)).thenReturn(county);

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = ow.writeValueAsString(county);

        mockMvc.perform(post("/county")
                .contentType(MediaType.APPLICATION_JSON).content(requestJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("name", Matchers.is("Adana")));
    }

    @Test
    public void whenUpdateCounty_thenReturnCounty() throws Exception {

        County county = County.builder()
                .id(1L)
                .name("Adana")
                .build();

        county.setName("ADANA");

        Mockito.when(countyService.update(county)).thenReturn(county);

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = ow.writeValueAsString(county);

        mockMvc.perform(put("/county")
                .contentType(MediaType.APPLICATION_JSON).content(requestJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("name", Matchers.is("ADANA")));
    }

    @Test
    public void whenDeleteCounty_thenReturnCounty() throws Exception {

        County county = County.builder()
                .id(1L)
                .name("Adana")
                .build();

        Mockito.when(countyService.deleteById(1L)).thenReturn(county);

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = ow.writeValueAsString(county);

        mockMvc.perform(delete("/county/1")
                .contentType(MediaType.APPLICATION_JSON).content(requestJson))
                .andExpect(status().isOk());
    }


}
