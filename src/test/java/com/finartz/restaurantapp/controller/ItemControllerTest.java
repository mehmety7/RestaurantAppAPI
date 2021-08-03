package com.finartz.restaurantapp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.finartz.restaurantapp.model.Item;
import com.finartz.restaurantapp.service.ItemService;
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
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@RunWith(SpringRunner.class)
@WebMvcTest(ItemController.class)
public class ItemControllerTest {

    public static final String NAME_HAMBURGER = "Hamburger";
    public static final String NAME_CHEESEBURGER = "Cheeseburger";
    public static final String URI_ITEM = "/item";


    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ItemService itemService;

    @MockBean
    private UserDetailsService userDetailsService;

    @Test
    public void whenGetAll_thenReturnAllItems() throws Exception {

        Item item = Item.builder().id(1l).name(NAME_HAMBURGER).build();
        List<Item> itemList = Arrays.asList(item);

        Mockito.when(itemService.getAll()).thenReturn(itemList);

        mockMvc.perform(get(URI_ITEM)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(1)))
                .andExpect(jsonPath("$[0].name", Matchers.is(NAME_HAMBURGER)));

    }

    @Test
    public void whenGetById_thenReturnItem() throws Exception {

        Item item = Item.builder().id(1l).name(NAME_HAMBURGER).build();

        Mockito.when(itemService.getById(1l)).thenReturn(item);

        mockMvc.perform(get(URI_ITEM + "/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", Matchers.is(NAME_HAMBURGER)));

    }

    @Test
    public void whenCreateNewItem_thenReturnItem() throws Exception {

        Item item = Item.builder().id(1l).name(NAME_HAMBURGER).build();

        Mockito.when(itemService.create(item)).thenReturn(item);

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = ow.writeValueAsString(item);

        mockMvc.perform(post(URI_ITEM)
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", Matchers.is(NAME_HAMBURGER)));
    }

    @Test
    public void whenUpdateItem_thenReturnItem() throws Exception {

        Item item = Item.builder()
                .id(1L)
                .name(NAME_HAMBURGER)
                .build();

        Item modifyItem = Item.builder()
                .id(1l)
                .name(NAME_CHEESEBURGER)
                .build();

        Mockito.when(itemService.create(item)).thenReturn(item);
        Mockito.when(itemService.update(modifyItem)).thenReturn(modifyItem);

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson1 = ow.writeValueAsString(item);
        String requestJson2 = ow.writeValueAsString(modifyItem);

        mockMvc.perform(post(URI_ITEM)
                .contentType(MediaType.APPLICATION_JSON).content(requestJson1))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", Matchers.is(NAME_HAMBURGER)))
                .andExpect(jsonPath("$.id", Matchers.is(1)));

        mockMvc.perform(put(URI_ITEM)
                .contentType(MediaType.APPLICATION_JSON).content(requestJson2))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", Matchers.is(NAME_CHEESEBURGER)))
                .andExpect(jsonPath("$.id", Matchers.is(1)));

    }

    @Test
    public void whenDeleteItem_thenReturnItem() throws Exception {

        Item item = Item.builder()
                .id(1L)
                .name(NAME_HAMBURGER)
                .build();

        Mockito.when(itemService.deleteById(1L)).thenReturn(item);

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = ow.writeValueAsString(item);

        mockMvc.perform(delete(URI_ITEM + "/1")
                .contentType(MediaType.APPLICATION_JSON).content(requestJson))
                .andExpect(status().isOk());
    }

}
