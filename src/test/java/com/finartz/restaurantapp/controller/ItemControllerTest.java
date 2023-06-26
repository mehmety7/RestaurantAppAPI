package com.finartz.restaurantapp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.finartz.restaurantapp.model.dto.ItemDto;
import com.finartz.restaurantapp.model.dto.PageDto;
import com.finartz.restaurantapp.model.request.create.ItemCreateRequest;
import com.finartz.restaurantapp.model.request.get.ItemPageGetRequest;
import com.finartz.restaurantapp.service.ItemService;
import org.hamcrest.Matchers;
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

import static org.mockito.ArgumentMatchers.anyString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@RunWith(SpringRunner.class)
@WebMvcTest(ItemController.class)
@AutoConfigureMockMvc(addFilters = false)
public class ItemControllerTest {

    public static final String NAME_HAMBURGER = "Hamburger";
    public static final String URI_ITEM = "/item";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ItemService itemService;

    @MockBean
    private UserDetailsService userDetailsService;

    @Test
    public void whenGetAll_thenReturnAllItems() throws Exception {

        ItemPageGetRequest itemPageGetRequest = ItemPageGetRequest.builder().pageNo(0).pageSize(1).sortBy("id").build();
        ItemDto item = ItemDto.builder().id(1L).name(NAME_HAMBURGER).unitType("piece").build();
        List<ItemDto> items = Collections.singletonList(item);
        PageDto<ItemDto> pageDto = new PageDto<>(items, 1, 1);

        Mockito.when(itemService.getItems(itemPageGetRequest)).thenReturn(pageDto);

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = ow.writeValueAsString(itemPageGetRequest);

        mockMvc.perform(get(URI_ITEM)
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson))
                .andExpect(status().isOk());

    }

    @Test
    public void whenGetById_thenReturnItem() throws Exception {

        ItemDto item = ItemDto.builder().id(1L).name(NAME_HAMBURGER).build();

        Mockito.when(itemService.getItem(1L)).thenReturn(item);

        mockMvc.perform(get(URI_ITEM + "/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", Matchers.is(NAME_HAMBURGER)));

    }

    @Test
    public void whenCreateNewItem_thenReturnItem() throws Exception {

        ItemDto item = ItemDto.builder().id(1L).name(NAME_HAMBURGER).build();
        ItemCreateRequest itemCreateRequest = ItemCreateRequest.builder().name(NAME_HAMBURGER).unitType(anyString()).build();

        Mockito.when(itemService.createItem(itemCreateRequest)).thenReturn(item);

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = ow.writeValueAsString(itemCreateRequest);

        mockMvc.perform(post(URI_ITEM)
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson))
                .andExpect(status().isCreated());
    }

}
