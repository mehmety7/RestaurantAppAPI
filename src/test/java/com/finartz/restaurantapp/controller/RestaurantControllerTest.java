package com.finartz.restaurantapp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.finartz.restaurantapp.model.Branch;
import com.finartz.restaurantapp.model.Restaurant;
import com.finartz.restaurantapp.model.User;
import com.finartz.restaurantapp.model.enumerated.Status;
import com.finartz.restaurantapp.service.RestaurantService;
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
@WebMvcTest(RestaurantController.class)
public class RestaurantControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RestaurantService restaurantService;

    @Test
    public void whenGetAllRestaurant_thenReturnRestaurant() throws Exception {

        User user = User.builder().build();

        Branch branch = Branch.builder().build();

        Restaurant restaurant = Restaurant.builder()
                .id(1L)
                .name("Kral Burger")
                .status(Status.APPROVED)
                .user(user)
                .branchList(Arrays.asList(branch))
                .build();

        List<Restaurant> restaurantList = Arrays.asList(restaurant);

        Mockito.when(restaurantService.getAll()).thenReturn(restaurantList);

        mockMvc.perform(get("/restaurant")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(1)))
                .andExpect(jsonPath("$[0].name", Matchers.is("Kral Burger")));

    }

    @Test
    public void whenGetRestaurantById_thenReturnRestaurant() throws Exception {

        User user = User.builder().build();

        Branch branch = Branch.builder().build();

        Restaurant restaurant = Restaurant.builder()
                .id(1L)
                .name("Kral Burger")
                .status(Status.APPROVED)
                .user(user)
                .branchList(Arrays.asList(branch))
                .build();

        Mockito.when(restaurantService.getById(1L)).thenReturn(restaurant);

        mockMvc.perform(get("/restaurant/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("name", Matchers.is("Kral Burger")));

    }

    @Test
    public void whenCreateNewRestaurant_thenReturnRestaurant() throws Exception {

        User user = User.builder().build();

        Branch branch = Branch.builder().build();

        Restaurant restaurant = Restaurant.builder()
                .id(1L)
                .name("Kral Burger")
                .status(Status.APPROVED)
                .user(user)
                .branchList(Arrays.asList(branch))
                .build();

        Mockito.when(restaurantService.create(restaurant)).thenReturn(restaurant);

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = ow.writeValueAsString(restaurant);

        mockMvc.perform(post("/restaurant")
                .contentType(MediaType.APPLICATION_JSON).content(requestJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("name", Matchers.is("Kral Burger")));
    }

    @Test
    public void whenUpdateRestaurant_thenReturnRestaurant() throws Exception {

        User user = User.builder().build();

        Branch branch = Branch.builder().build();

        Restaurant restaurant = Restaurant.builder()
                .id(1L)
                .name("Kral Burger")
                .status(Status.APPROVED)
                .user(user)
                .branchList(Arrays.asList(branch))
                .build();

        restaurant.setName("Kral Restaurant");

        Mockito.when(restaurantService.update(restaurant)).thenReturn(restaurant);

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = ow.writeValueAsString(restaurant);

        mockMvc.perform(put("/restaurant")
                .contentType(MediaType.APPLICATION_JSON).content(requestJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("name", Matchers.is("Kral Restaurant")));
    }

    @Test
    public void whenDeleteRestaurant_thenReturnRestaurant() throws Exception {

        User user = User.builder().build();

        Branch branch = Branch.builder().build();

        Restaurant restaurant = Restaurant.builder()
                .id(1L)
                .name("Kral Burger")
                .status(Status.APPROVED)
                .user(user)
                .branchList(Arrays.asList(branch))
                .build();

        Mockito.when(restaurantService.deleteById(1L)).thenReturn(restaurant);

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = ow.writeValueAsString(restaurant);

        mockMvc.perform(delete("/restaurant/1")
                .contentType(MediaType.APPLICATION_JSON).content(requestJson))
                .andExpect(status().isOk());
    }

    @Test
    public void whenGetRestaurantIsWaiting_thenReturnRestaurant() throws Exception {

        User user = User.builder().build();

        Branch branch = Branch.builder().build();

        Restaurant restaurant = Restaurant.builder()
                .id(1L)
                .name("Kral Burger")
                .status(Status.WAITING)
                .user(user)
                .branchList(Arrays.asList(branch))
                .build();

        List<Restaurant> restaurantList = Arrays.asList(restaurant);

        Mockito.when(restaurantService.findByStatus(Status.WAITING)).thenReturn(restaurantList);

        mockMvc.perform(get("/restaurant/waiting")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(1)));

    }


}
