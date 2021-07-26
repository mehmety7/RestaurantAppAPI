package com.finartz.restaurantapp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.finartz.restaurantapp.model.*;
import com.finartz.restaurantapp.model.enumerated.Role;
import com.finartz.restaurantapp.service.BasketService;
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
@WebMvcTest(BasketController.class)
public class BasketControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BasketService basketService;

    @Test
    public void whenGetAllBasket_thenReturnBasket() throws Exception {

        User user = User.builder()
                .name("Ali Akay")
                .email("ali@gmail.com")
                .password("ali1212")
                .role(Role.USER).build();

        BasketMeal basketMeal = BasketMeal.builder().build();

        Basket basket = Basket.builder()
                .basketMealList(Arrays.asList(basketMeal))
                .user(user)
                .totalPrice(100.99)
                .id(1L)
                .build();

        List<Basket> basketList = Arrays.asList(basket);

        Mockito.when(basketService.getAll()).thenReturn(basketList);

        mockMvc.perform(get("/basket")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(1)))
                .andExpect(jsonPath("$[0].user.name", Matchers.is("Ali Akay")));
    }

    @Test
    public void whenGetByBasketId_thenReturnBasket() throws Exception {

        User user = User.builder()
                .name("Ali Akay")
                .email("ali@gmail.com")
                .password("ali1212")
                .role(Role.USER).build();

        BasketMeal basketMeal = BasketMeal.builder().build();

        Basket basket = Basket.builder()
                .basketMealList(Arrays.asList(basketMeal))
                .user(user)
                .totalPrice(100.99)
                .id(1L)
                .build();

        Mockito.when(basketService.getById(1L)).thenReturn(basket);

        mockMvc.perform(get("/basket/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("user.name", Matchers.is("Ali Akay")));

    }

    @Test
    public void whenCreateNewBasket_thenReturnCreated() throws Exception {

        User user = User.builder()
                .name("Ali Akay")
                .email("ali@gmail.com")
                .password("ali1212")
                .role(Role.USER).build();

        BasketMeal basketMeal = BasketMeal.builder().build();

        Basket basket = Basket.builder()
                .basketMealList(Arrays.asList(basketMeal))
                .user(user)
                .totalPrice(100.99)
                .id(1L)
                .build();


        Mockito.when(basketService.create(basket)).thenReturn(basket);

        // to-do : https://stackoverflow.com/questions/20504399/testing-springs-requestbody-using-spring-mockmvc
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = ow.writeValueAsString(basket);

        mockMvc.perform(post("/basket")
                .contentType(MediaType.APPLICATION_JSON).content(requestJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("user.name", Matchers.is("Ali Akay")));

    }

    @Test
    public void whenUpdateExistsBasket_thenReturnUpdated() throws Exception {

        User user = User.builder()
                .name("Ali Akay")
                .email("ali@gmail.com")
                .password("ali1212")
                .role(Role.USER).build();

        BasketMeal basketMeal = BasketMeal.builder().build();

        Basket basket = Basket.builder()
                .basketMealList(Arrays.asList(basketMeal))
                .user(user)
                .totalPrice(100.99)
                .id(1L)
                .build();

        user.setName("Ali Kınay");
        basket.setUser(user);

        Mockito.when(basketService.update(basket)).thenReturn(basket);

        // to-do : https://stackoverflow.com/questions/20504399/testing-springs-requestbody-using-spring-mockmvc
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = ow.writeValueAsString(basket);

        mockMvc.perform(put("/basket")
                .contentType(MediaType.APPLICATION_JSON).content(requestJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("user.name", Matchers.is("Ali Kınay")));

    }

    @Test
    public void whenDeleteExistsBasket_thenReturnOk() throws Exception {

        User user = User.builder()
                .name("Ali Akay")
                .email("ali@gmail.com")
                .password("ali1212")
                .role(Role.USER).build();

        BasketMeal basketMeal = BasketMeal.builder().build();

        Basket basket = Basket.builder()
                .basketMealList(Arrays.asList(basketMeal))
                .user(user)
                .totalPrice(100.99)
                .id(1L)
                .build();


        Mockito.when(basketService.deleteById(1L)).thenReturn(basket);

        mockMvc.perform(delete("/basket/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

}
