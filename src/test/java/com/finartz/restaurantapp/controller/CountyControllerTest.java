package com.finartz.restaurantapp.controller;

import com.finartz.restaurantapp.model.dto.CountyDto;
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
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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

    @MockBean
    private UserDetailsService userDetailsService;

    @Test
    public void whenGetCountyById_thenReturnCounty() throws Exception {

        CountyDto county = CountyDto.builder()
                .id(1L)
                .name(COUNTY_KADIKOY)
                .build();

        Mockito.when(countyService.getCounty(1L)).thenReturn(county);

        mockMvc.perform(get(URI_COUNTY + "/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("name", Matchers.is(COUNTY_KADIKOY)));

    }

    @Test
    public void whenGetCountyByCityId_thenReturnCounties() throws Exception {

        CountyDto county = CountyDto.builder()
                .cityId(1L)
                .name(COUNTY_KADIKOY)
                .build();

        Mockito.when(countyService.getCounties(1L)).thenReturn(Arrays.asList(county));

        mockMvc.perform(get(URI_COUNTY + "?city_id=1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name", Matchers.is(COUNTY_KADIKOY)));

    }

}
