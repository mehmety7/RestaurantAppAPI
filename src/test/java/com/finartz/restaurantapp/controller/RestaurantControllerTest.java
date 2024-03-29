package com.finartz.restaurantapp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.finartz.restaurantapp.model.dto.RestaurantDto;
import com.finartz.restaurantapp.model.enumerated.RestaurantStatus;
import com.finartz.restaurantapp.model.request.create.RestaurantCreateRequest;
import com.finartz.restaurantapp.model.request.update.RestaurantUpdateRequest;
import com.finartz.restaurantapp.service.RestaurantService;
import org.hamcrest.Matchers;
import org.junit.Before;
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

import java.util.Collections;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@ExtendWith(SpringExtension.class)
@WebMvcTest(RestaurantController.class)
@AutoConfigureMockMvc(addFilters = false)
public class RestaurantControllerTest {

    private final String URI_RESTAURANT = "/restaurant";
    private final String NAME_KRAL_BURGER = "Kral Burger";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RestaurantService restaurantService;

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
    public void whenGetRestaurantById_thenReturnRestaurant() throws Exception {

        RestaurantDto restaurant = RestaurantDto.builder()
                .id(1L)
                .restaurantStatus(RestaurantStatus.WAITING)
                .build();

        Mockito.when(restaurantService.getRestaurant(1L)).thenReturn(restaurant);

        mockMvc.perform(get(URI_RESTAURANT + "/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("restaurantStatus", Matchers.is(RestaurantStatus.WAITING.toString())));

    }

    @Test
    public void whenGetRestaurantIsWaiting_thenReturnRestaurant() throws Exception {

        RestaurantDto restaurant = RestaurantDto.builder()
                .id(1L)
                .restaurantStatus(RestaurantStatus.WAITING)
                .build();

        List<RestaurantDto> restaurantList = Collections.singletonList(restaurant);

        Mockito.when(restaurantService.getRestaurants(RestaurantStatus.WAITING)).thenReturn(restaurantList);

        mockMvc.perform(get(URI_RESTAURANT + "/waiting")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(1)));

    }

    @Test
    public void whenCreateNewRestaurant_thenReturnRestaurant() throws Exception {

        RestaurantDto restaurant = RestaurantDto.builder()
                .id(1L)
                .name(NAME_KRAL_BURGER)
                .userId(1L)
                .restaurantStatus(RestaurantStatus.WAITING)
                .build();

        RestaurantCreateRequest restaurantCreateRequest = RestaurantCreateRequest
                .builder()
                .name(NAME_KRAL_BURGER)
                .userId(1L)
                .build();

        Mockito.when(restaurantService.createRestaurant(restaurantCreateRequest)).thenReturn(restaurant);

        String requestJson = objectWriter.writeValueAsString(restaurantCreateRequest);

        mockMvc.perform(post(URI_RESTAURANT)
                .contentType(MediaType.APPLICATION_JSON).content(requestJson))
                .andExpect(status().isCreated());
    }

    @Test
    public void whenUpdateRestaurant_thenReturnRestaurant() throws Exception {

        RestaurantDto restaurant = RestaurantDto.builder()
                .id(1L)
                .name(NAME_KRAL_BURGER)
                .userId(1L)
                .restaurantStatus(RestaurantStatus.WAITING)
                .build();

        RestaurantDto restaurantUpdate = RestaurantDto.builder()
                .id(1L)
                .name(NAME_KRAL_BURGER)
                .userId(1L)
                .restaurantStatus(RestaurantStatus.APPROVED)
                .build();

        RestaurantCreateRequest restaurantCreateRequest = RestaurantCreateRequest
                .builder()
                .name(NAME_KRAL_BURGER)
                .userId(1L)
                .build();

        RestaurantUpdateRequest restaurantUpdateRequest = RestaurantUpdateRequest
                .builder()
                .id(1L)
                .restaurantStatus(RestaurantStatus.APPROVED)
                .build();



        Mockito.when(restaurantService.createRestaurant(restaurantCreateRequest)).thenReturn(restaurant);
        Mockito.when(restaurantService.updateRestaurantStatus(restaurantUpdateRequest)).thenReturn(restaurantUpdate);

        String requestJson1 = objectWriter.writeValueAsString(restaurantCreateRequest);
        String requestJson2 = objectWriter.writeValueAsString(restaurantUpdateRequest);

        mockMvc.perform(post(URI_RESTAURANT)
                .contentType(MediaType.APPLICATION_JSON).content(requestJson1))
                .andExpect(status().isCreated());

        mockMvc.perform(put(URI_RESTAURANT)
                .contentType(MediaType.APPLICATION_JSON).content(requestJson2))
                .andExpect(status().isOk());
    }


}
