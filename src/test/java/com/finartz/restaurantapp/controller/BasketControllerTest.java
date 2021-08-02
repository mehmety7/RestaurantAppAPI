package com.finartz.restaurantapp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.finartz.restaurantapp.model.Basket;
import com.finartz.restaurantapp.model.BasketMeal;
import com.finartz.restaurantapp.model.User;
import com.finartz.restaurantapp.service.BasketService;
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

@ExtendWith(SpringExtension.class)
@RunWith(SpringRunner.class)
@WebMvcTest(BasketController.class)
public class BasketControllerTest {

    private static final String URI_BASKET = "/basket";
    private static final String USER_ALI_AKAY = "Ali Akay";
    private static final String USER_EMAIL = "ali@gmail.com";
    private static final String USER_PASSWORD = "ali1212";
    private static final Double PRICE_100 = 100.0;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BasketService basketService;

    private ObjectWriter objectWriter;

    @Before
    public void init() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        objectWriter = mapper.writer().withDefaultPrettyPrinter();
    }

    @Test
    public void whenGetAllBasket_thenReturnBasket() throws Exception {

        User user = User.builder()
                .name(USER_ALI_AKAY)
                .email(USER_EMAIL)
                .password(USER_PASSWORD)
                .build();

        BasketMeal basketMeal = BasketMeal.builder().build();

        Basket basket = Basket.builder()
                .basketMealList(Arrays.asList(basketMeal))
                .user(user)
                .totalPrice(PRICE_100)
                .id(1L)
                .build();

        List<Basket> basketList = Arrays.asList(basket);

        Mockito.when(basketService.getAll()).thenReturn(basketList);

        mockMvc.perform(get(URI_BASKET)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(1)))
                .andExpect(jsonPath("$[0].user.name", Matchers.is(USER_ALI_AKAY)));
    }

    @Test
    public void whenGetByBasketId_thenReturnBasket() throws Exception {

        User user = User.builder()
                .name(USER_ALI_AKAY)
                .email(USER_EMAIL)
                .password(USER_PASSWORD)
                .build();

        BasketMeal basketMeal = BasketMeal.builder().build();

        Basket basket = Basket.builder()
                .basketMealList(Arrays.asList(basketMeal))
                .user(user)
                .totalPrice(PRICE_100)
                .id(1L)
                .build();

        Mockito.when(basketService.getById(1L)).thenReturn(basket);

        mockMvc.perform(get(URI_BASKET + "/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("user.name", Matchers.is(USER_ALI_AKAY)));

    }

    @Test
    public void whenCreateNewBasket_thenReturnCreated() throws Exception {

        User user = User.builder()
                .name(USER_ALI_AKAY)
                .email(USER_EMAIL)
                .password(USER_PASSWORD)
                .build();

        BasketMeal basketMeal = BasketMeal.builder().build();

        Basket basket = Basket.builder()
                .basketMealList(Arrays.asList(basketMeal))
                .user(user)
                .totalPrice(PRICE_100)
                .id(1L)
                .build();


        Mockito.when(basketService.create(basket)).thenReturn(basket);

        String requestJson = objectWriter.writeValueAsString(basket);

        mockMvc.perform(post(URI_BASKET)
                .contentType(MediaType.APPLICATION_JSON).content(requestJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("user.name", Matchers.is(USER_ALI_AKAY)));

    }

    @Test
    public void whenUpdateExistsBasket_thenReturnUpdated() throws Exception {

        User user = User.builder()
                .name(USER_ALI_AKAY)
                .email(USER_EMAIL)
                .password(USER_PASSWORD)
                .build();

        BasketMeal basketMeal = BasketMeal.builder().build();

        Basket basket = Basket.builder()
                .basketMealList(Arrays.asList(basketMeal))
                .user(user)
                .totalPrice(PRICE_100)
                .id(1L)
                .build();

        Basket modifyBasket = Basket.builder().id(1l).user(user).totalPrice(PRICE_100 + 100.00).build();

        Mockito.when(basketService.create(basket)).thenReturn(basket);
        Mockito.when(basketService.update(modifyBasket)).thenReturn(modifyBasket);

        String requestJson1 = objectWriter.writeValueAsString(basket);
        String requestJson2 = objectWriter.writeValueAsString(modifyBasket);

        mockMvc.perform(post(URI_BASKET)
                .contentType(MediaType.APPLICATION_JSON).content(requestJson1))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.totalPrice", Matchers.is(PRICE_100)))
                .andExpect(jsonPath("$.id", Matchers.is(1)));

        mockMvc.perform(put(URI_BASKET)
                .contentType(MediaType.APPLICATION_JSON).content(requestJson2))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.totalPrice", Matchers.is(PRICE_100+100.00)))
                .andExpect(jsonPath("$.id", Matchers.is(1)));

    }

    @Test
    public void whenDeleteExistsBasket_thenReturnOk() throws Exception {

        User user = User.builder()
                .name(USER_ALI_AKAY)
                .email(USER_EMAIL)
                .password(USER_PASSWORD)
                .build();

        BasketMeal basketMeal = BasketMeal.builder().build();

        Basket basket = Basket.builder()
                .basketMealList(Arrays.asList(basketMeal))
                .user(user)
                .totalPrice(PRICE_100)
                .id(1L)
                .build();


        Mockito.when(basketService.deleteById(1L)).thenReturn(basket);

        mockMvc.perform(delete(URI_BASKET + "/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

}
