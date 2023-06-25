package com.finartz.restaurantapp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.finartz.restaurantapp.model.dto.MenuDto;
import com.finartz.restaurantapp.model.request.create.MenuCreateRequest;
import com.finartz.restaurantapp.service.MenuService;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@ExtendWith(SpringExtension.class)
@WebMvcTest(MenuController.class)
@AutoConfigureMockMvc(addFilters = false)
public class MenuControllerTest {

    private final static String URI_MENU = "/menu";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MenuService menuService;

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
    public void whenGetMenuById_thenReturnMenu() throws Exception {

        MenuDto menu = MenuDto.builder().id(1L).build();

        Mockito.when(menuService.getMenu(1L)).thenReturn(menu);

        mockMvc.perform(get(URI_MENU + "/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("id", Matchers.is(1)));

    }

    @Test
    public void whenGetMenuByBranchId_thenReturnBrancheMenu() throws Exception {

        MenuDto menu = MenuDto.builder().id(1L).build();

        Mockito.when(menuService.getBranchMenu(1L)).thenReturn(menu);

        mockMvc.perform(get(URI_MENU + "/branch?branch_id=1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("id", Matchers.is(1)));

    }

    @Test
    public void whenCreateNewMenu_thenReturnMenu() throws Exception {

        MenuDto menu = MenuDto.builder().id(1L).build();
        MenuCreateRequest menuCreateRequest = MenuCreateRequest.builder().branchId(1L).build();

        Mockito.when(menuService.createMenu(menuCreateRequest)).thenReturn(menu);

        String requestJson = objectWriter.writeValueAsString(menuCreateRequest);

        mockMvc.perform(post(URI_MENU)
                .contentType(MediaType.APPLICATION_JSON).content(requestJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("id", Matchers.is(1)));
    }

}
