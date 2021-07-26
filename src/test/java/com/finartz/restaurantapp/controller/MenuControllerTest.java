package com.finartz.restaurantapp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.finartz.restaurantapp.model.Branch;
import com.finartz.restaurantapp.model.Meal;
import com.finartz.restaurantapp.model.Menu;
import com.finartz.restaurantapp.service.MenuService;
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
@WebMvcTest(MenuController.class)
public class MenuControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MenuService menuService;

    @Test
    public void whenGetAllMenu_thenReturnMenu() throws Exception {

        Branch branch = Branch.builder().name("Kral Burger Şişli").build();

        Meal meal = Meal.builder().build();

        Menu menu = Menu.builder()
                .id(1L)
                .branch(branch)
                .mealList(Arrays.asList(meal))
                .build();

        List<Menu> menuList = Arrays.asList(menu);

        Mockito.when(menuService.getAll()).thenReturn(menuList);

        mockMvc.perform(get("/menu")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(1)))
                .andExpect(jsonPath("$[0].branch.name", Matchers.is("Kral Burger Şişli")));

    }

    @Test
    public void whenGetMenuById_thenReturnMenu() throws Exception {

        Branch branch = Branch.builder().name("Kral Burger Şişli").build();

        Meal meal = Meal.builder().build();

        Menu menu = Menu.builder()
                .id(1L)
                .branch(branch)
                .mealList(Arrays.asList(meal))
                .build();

        Mockito.when(menuService.getById(1L)).thenReturn(menu);

        mockMvc.perform(get("/menu/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("branch.name", Matchers.is("Kral Burger Şişli")));

    }

    @Test
    public void whenCreateNewMenu_thenReturnMenu() throws Exception {

        Branch branch = Branch.builder().name("Kral Burger Şişli").build();

        Meal meal = Meal.builder().build();

        Menu menu = Menu.builder()
                .id(1L)
                .branch(branch)
                .mealList(Arrays.asList(meal))
                .build();

        Mockito.when(menuService.create(menu)).thenReturn(menu);

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = ow.writeValueAsString(menu);

        mockMvc.perform(post("/menu")
                .contentType(MediaType.APPLICATION_JSON).content(requestJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("branch.name", Matchers.is("Kral Burger Şişli")));
    }

    @Test
    public void whenUpdateMenu_thenReturnMenu() throws Exception {

        Branch branch = Branch.builder().name("Kral Burger Şişli").build();

        Meal meal = Meal.builder().build();

        Menu menu = Menu.builder()
                .id(1L)
                .branch(branch)
                .mealList(Arrays.asList(meal))
                .build();

        branch.setName("Kral Burger Mecidiyeköy");
        menu.setBranch(branch);

        Mockito.when(menuService.update(menu)).thenReturn(menu);

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = ow.writeValueAsString(menu);

        mockMvc.perform(put("/menu")
                .contentType(MediaType.APPLICATION_JSON).content(requestJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("branch.name", Matchers.is("Kral Burger Mecidiyeköy")));
    }

    @Test
    public void whenDeleteMenu_thenReturnMenu() throws Exception {

        Branch branch = Branch.builder().name("Kral Burger Şişli").build();

        Meal meal = Meal.builder().build();

        Menu menu = Menu.builder()
                .id(1L)
                .branch(branch)
                .mealList(Arrays.asList(meal))
                .build();

        Mockito.when(menuService.deleteById(1L)).thenReturn(menu);

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = ow.writeValueAsString(menu);

        mockMvc.perform(delete("/menu/1")
                .contentType(MediaType.APPLICATION_JSON).content(requestJson))
                .andExpect(status().isOk());
    }


}
