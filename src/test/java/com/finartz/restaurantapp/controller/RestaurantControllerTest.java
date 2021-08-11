package com.finartz.restaurantapp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.finartz.restaurantapp.model.dto.RestaurantDto;
import com.finartz.restaurantapp.model.enumerated.Status;
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
@WebMvcTest(RestaurantController.class)
public class RestaurantControllerTest {

    private final String URI_RESTAURANT = "/restaurant";

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
                .status(Status.WAITING)
                .build();

        Mockito.when(restaurantService.getRestaurant(1L)).thenReturn(restaurant);

        mockMvc.perform(get(URI_RESTAURANT + "/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("status", Matchers.is(Status.WAITING.toString())));

    }

    @Test
    public void whenCreateNewRestaurant_thenReturnRestaurant() throws Exception {

        RestaurantDto restaurant = RestaurantDto.builder()
                .id(1L)
                .status(Status.WAITING)
                .build();

        RestaurantCreateRequest restaurantCreateRequest = RestaurantCreateRequest.builder().build();

        Mockito.when(restaurantService.createRestaurant(restaurantCreateRequest)).thenReturn(restaurant);

        String requestJson = objectWriter.writeValueAsString(restaurant);

        mockMvc.perform(post(URI_RESTAURANT)
                .contentType(MediaType.APPLICATION_JSON).content(requestJson))
                .andExpect(status().isCreated());
    }

    @Test
    public void whenGetRestaurantIsWaiting_thenReturnRestaurant() throws Exception {

        RestaurantDto restaurant = RestaurantDto.builder()
                .id(1L)
                .status(Status.WAITING)
                .build();

        List<RestaurantDto> restaurantList = Arrays.asList(restaurant);

        Mockito.when(restaurantService.getRestaurants(Status.WAITING)).thenReturn(restaurantList);

        mockMvc.perform(get(URI_RESTAURANT + "/waiting")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(1)));

    }

    @Test
    public void whenUpdateRestaurant_thenReturnRestaurant() throws Exception {

        RestaurantDto restaurant = RestaurantDto.builder()
                .id(1L)
                .status(Status.WAITING)
                .build();

        RestaurantDto restaurantUpdate = RestaurantDto.builder()
                .id(1L)
                .status(Status.APPROVED)
                .build();

        RestaurantCreateRequest restaurantCreateRequest = RestaurantCreateRequest.builder().build();
        RestaurantUpdateRequest restaurantUpdateRequest = RestaurantUpdateRequest.builder().build();

        Mockito.when(restaurantService.createRestaurant(restaurantCreateRequest)).thenReturn(restaurant);
        Mockito.when(restaurantService.updateRestaurant(1L, restaurantUpdateRequest)).thenReturn(restaurantUpdate);

        String requestJson1 = objectWriter.writeValueAsString(restaurant);
        String requestJson2 = objectWriter.writeValueAsString(restaurantUpdate);

        mockMvc.perform(post(URI_RESTAURANT)
                .contentType(MediaType.APPLICATION_JSON).content(requestJson1))
                .andExpect(status().isCreated());

        mockMvc.perform(put(URI_RESTAURANT + "/1")
                .contentType(MediaType.APPLICATION_JSON).content(requestJson2))
                .andExpect(status().isOk());
    }


}
