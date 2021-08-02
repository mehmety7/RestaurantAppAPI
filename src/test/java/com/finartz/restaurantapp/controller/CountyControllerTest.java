package com.finartz.restaurantapp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.finartz.restaurantapp.model.County;
import com.finartz.restaurantapp.service.CountyService;
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

    private static final String URI_COUNTY = "/county";
    private static final String COUNTY_KADIKOY = "Kadıköy";
    private static final String COUNTY_SISLI = "Şişli";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CountyService countyService;

    private ObjectWriter objectWriter;

    @Before
    public void init() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        objectWriter = mapper.writer().withDefaultPrettyPrinter();
    }

    @Test
    public void whenGetAllCounty_thenReturnCounty() throws Exception {

        County county = County.builder()
                .id(1L)
                .name(COUNTY_KADIKOY)
                .build();

        List<County> countyList = Arrays.asList(county);

        Mockito.when(countyService.getAll()).thenReturn(countyList);

        mockMvc.perform(get(URI_COUNTY)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(1)))
                .andExpect(jsonPath("$[0].name", Matchers.is(COUNTY_KADIKOY)));

    }

    @Test
    public void whenGetCountyById_thenReturnCounty() throws Exception {

        County county = County.builder()
                .id(1L)
                .name(COUNTY_KADIKOY)
                .build();

        Mockito.when(countyService.getById(1L)).thenReturn(county);

        mockMvc.perform(get(URI_COUNTY + "/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("name", Matchers.is(COUNTY_KADIKOY)));

    }

    @Test
    public void whenCreateNewCounty_thenReturnCounty() throws Exception {

        County county = County.builder()
                .id(1L)
                .name(COUNTY_KADIKOY)
                .build();

        Mockito.when(countyService.create(county)).thenReturn(county);

        String requestJson = objectWriter.writeValueAsString(county);

        mockMvc.perform(post(URI_COUNTY)
                .contentType(MediaType.APPLICATION_JSON).content(requestJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("name", Matchers.is(COUNTY_KADIKOY)));
    }

    @Test
    public void whenUpdateCounty_thenReturnCounty() throws Exception {

        County county = County.builder()
                .id(1L)
                .name(COUNTY_KADIKOY)
                .build();

        County modifyCounty = County.builder()
                .id(1l)
                .name(COUNTY_SISLI)
                .build();

        Mockito.when(countyService.create(county)).thenReturn(county);
        Mockito.when(countyService.update(modifyCounty)).thenReturn(modifyCounty);

        String requestJson1 = objectWriter.writeValueAsString(county);
        String requestJson2 = objectWriter.writeValueAsString(modifyCounty);

        mockMvc.perform(post(URI_COUNTY)
                .contentType(MediaType.APPLICATION_JSON).content(requestJson1))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", Matchers.is(COUNTY_KADIKOY)))
                .andExpect(jsonPath("$.id", Matchers.is(1)));

        mockMvc.perform(put(URI_COUNTY)
                .contentType(MediaType.APPLICATION_JSON).content(requestJson2))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", Matchers.is(COUNTY_SISLI)))
                .andExpect(jsonPath("$.id", Matchers.is(1)));

    }

    @Test
    public void whenDeleteCounty_thenReturnCounty() throws Exception {

        County county = County.builder()
                .id(1L)
                .name(COUNTY_KADIKOY)
                .build();

        Mockito.when(countyService.deleteById(1L)).thenReturn(county);

        String requestJson = objectWriter.writeValueAsString(county);

        mockMvc.perform(delete(URI_COUNTY + "/1")
                .contentType(MediaType.APPLICATION_JSON).content(requestJson))
                .andExpect(status().isOk());
    }


}
