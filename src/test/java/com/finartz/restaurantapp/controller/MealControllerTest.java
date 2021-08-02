package com.finartz.restaurantapp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.finartz.restaurantapp.model.BasketMeal;
import com.finartz.restaurantapp.model.Item;
import com.finartz.restaurantapp.model.Meal;
import com.finartz.restaurantapp.service.MealService;
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
@WebMvcTest(MealController.class)
public class MealControllerTest {

    private static final String URI_MEAL = "/meal";
    private static final String NAME_KRAL_MENU = "Kral Menü";
    private static final String NAME_EFSANE_MENU = "Efsane Menü";
    private static final Double PRICE_15_99 = 15.99;


    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MealService mealService;

    private ObjectWriter objectWriter;

    @Before
    public void init() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        objectWriter = mapper.writer().withDefaultPrettyPrinter();
    }

    @Test
    public void whenGetAllMeal_thenReturnMeal() throws Exception {

        BasketMeal basketMeal = BasketMeal.builder().build();

        Item item = Item.builder().build();

        Meal meal = Meal.builder()
                .id(1L)
                .name(NAME_KRAL_MENU)
                .price(PRICE_15_99)
                .basketMealList(Arrays.asList(basketMeal))
                .itemList(Arrays.asList(item))
                .build();

        List<Meal> mealList = Arrays.asList(meal);

        Mockito.when(mealService.getAll()).thenReturn(mealList);

        mockMvc.perform(get(URI_MEAL)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(1)))
                .andExpect(jsonPath("$[0].name", Matchers.is(NAME_KRAL_MENU)));

    }

    @Test
    public void whenGetMealById_thenReturnMeal() throws Exception {

        BasketMeal basketMeal = BasketMeal.builder().build();

        Item item = Item.builder().build();

        Meal meal = Meal.builder()
                .id(1L)
                .name(NAME_KRAL_MENU)
                .price(PRICE_15_99)
                .basketMealList(Arrays.asList(basketMeal))
                .itemList(Arrays.asList(item))
                .build();

        Mockito.when(mealService.getById(1L)).thenReturn(meal);

        mockMvc.perform(get(URI_MEAL + "/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("name", Matchers.is(NAME_KRAL_MENU)));

    }

    @Test
    public void whenCreateNewMeal_thenReturnMeal() throws Exception {

        BasketMeal basketMeal = BasketMeal.builder().build();

        Item item = Item.builder().build();

        Meal meal = Meal.builder()
                .id(1L)
                .name(NAME_KRAL_MENU)
                .price(PRICE_15_99)
                .basketMealList(Arrays.asList(basketMeal))
                .itemList(Arrays.asList(item))
                .build();

        Mockito.when(mealService.create(meal)).thenReturn(meal);

        String requestJson = objectWriter.writeValueAsString(meal);

        mockMvc.perform(post(URI_MEAL)
                .contentType(MediaType.APPLICATION_JSON).content(requestJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("name", Matchers.is(NAME_KRAL_MENU)));
    }

    @Test
    public void whenUpdateMeal_thenReturnMeal() throws Exception {

        BasketMeal basketMeal = BasketMeal.builder().build();

        Item item = Item.builder().build();

        Meal meal = Meal.builder()
                .id(1L)
                .name(NAME_KRAL_MENU)
                .price(PRICE_15_99)
                .basketMealList(Arrays.asList(basketMeal))
                .itemList(Arrays.asList(item))
                .build();

        Meal modifyMeal = Meal.builder()
                .id(1L)
                .name(NAME_EFSANE_MENU)
                .price(PRICE_15_99)
                .basketMealList(Arrays.asList(basketMeal))
                .itemList(Arrays.asList(item))
                .build();

        Mockito.when(mealService.create(meal)).thenReturn(meal);
        Mockito.when(mealService.update(modifyMeal)).thenReturn(modifyMeal);

        String requestJson1 = objectWriter.writeValueAsString(meal);
        String requestJson2 = objectWriter.writeValueAsString(modifyMeal);

        mockMvc.perform(post(URI_MEAL)
                .contentType(MediaType.APPLICATION_JSON).content(requestJson1))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("name", Matchers.is(NAME_KRAL_MENU)))
                .andExpect(jsonPath("id", Matchers.is(1)));

        mockMvc.perform(put(URI_MEAL)
                .contentType(MediaType.APPLICATION_JSON).content(requestJson2))
                .andExpect(status().isOk())
                .andExpect(jsonPath("name", Matchers.is(NAME_EFSANE_MENU)))
                .andExpect(jsonPath("id", Matchers.is(1)));
    }

    @Test
    public void whenDeleteMeal_thenReturnMeal() throws Exception {

        BasketMeal basketMeal = BasketMeal.builder().build();

        Item item = Item.builder().build();

        Meal meal = Meal.builder()
                .id(1L)
                .name(NAME_KRAL_MENU)
                .price(PRICE_15_99)
                .basketMealList(Arrays.asList(basketMeal))
                .itemList(Arrays.asList(item))
                .build();

        Mockito.when(mealService.deleteById(1L)).thenReturn(meal);

        String requestJson = objectWriter.writeValueAsString(meal);

        mockMvc.perform(delete(URI_MEAL + "/1")
                .contentType(MediaType.APPLICATION_JSON).content(requestJson))
                .andExpect(status().isOk());
    }


}
