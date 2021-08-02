package com.finartz.restaurantapp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.finartz.restaurantapp.model.Branch;
import com.finartz.restaurantapp.model.Meal;
import com.finartz.restaurantapp.model.Menu;
import com.finartz.restaurantapp.service.MenuService;
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
@WebMvcTest(MenuController.class)
public class MenuControllerTest {

    private final static String URI_MENU = "/menu";
    private final static String NAME_KRAL_BURGER_SISLI = "Kral Burger Şişli";
    private final static String NAME_KRAL_BURGER_BESIKTAS = "Kral Burger Beşiktaş";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MenuService menuService;

    private ObjectWriter objectWriter;

    @Before
    public void init() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        objectWriter = mapper.writer().withDefaultPrettyPrinter();
    }

    @Test
    public void whenGetAllMenu_thenReturnMenu() throws Exception {

        Branch branch = Branch.builder().name(NAME_KRAL_BURGER_SISLI).build();

        Meal meal = Meal.builder().build();

        Menu menu = Menu.builder()
                .id(1L)
                .branch(branch)
                .mealList(Arrays.asList(meal))
                .build();

        List<Menu> menuList = Arrays.asList(menu);

        Mockito.when(menuService.getAll()).thenReturn(menuList);

        mockMvc.perform(get(URI_MENU)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(1)))
                .andExpect(jsonPath("$[0].branch.name", Matchers.is(NAME_KRAL_BURGER_SISLI)));

    }

    @Test
    public void whenGetMenuById_thenReturnMenu() throws Exception {

        Branch branch = Branch.builder().name(NAME_KRAL_BURGER_SISLI).build();

        Meal meal = Meal.builder().build();

        Menu menu = Menu.builder()
                .id(1L)
                .branch(branch)
                .mealList(Arrays.asList(meal))
                .build();

        Mockito.when(menuService.getById(1L)).thenReturn(menu);

        mockMvc.perform(get(URI_MENU + "/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("branch.name", Matchers.is(NAME_KRAL_BURGER_SISLI)));

    }

    @Test
    public void whenCreateNewMenu_thenReturnMenu() throws Exception {

        Branch branch = Branch.builder().name(NAME_KRAL_BURGER_SISLI).build();

        Meal meal = Meal.builder().build();

        Menu menu = Menu.builder()
                .id(1L)
                .branch(branch)
                .mealList(Arrays.asList(meal))
                .build();

        Mockito.when(menuService.create(menu)).thenReturn(menu);

        String requestJson = objectWriter.writeValueAsString(menu);

        mockMvc.perform(post(URI_MENU)
                .contentType(MediaType.APPLICATION_JSON).content(requestJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("branch.name", Matchers.is(NAME_KRAL_BURGER_SISLI)));
    }

    @Test
    public void whenUpdateMenu_thenReturnMenu() throws Exception {

        Branch branch = Branch.builder().name(NAME_KRAL_BURGER_SISLI).build();

        Meal meal = Meal.builder().build();

        Menu menu = Menu.builder()
                .id(1L)
                .branch(branch)
                .mealList(Arrays.asList(meal))
                .build();

        Branch modifyBranch = Branch.builder().name(NAME_KRAL_BURGER_BESIKTAS).build();

        Menu modifyMenu = Menu.builder()
                .id(1L)
                .branch(modifyBranch)
                .mealList(Arrays.asList(meal))
                .build();

        Mockito.when(menuService.create(menu)).thenReturn(menu);
        Mockito.when(menuService.update(modifyMenu)).thenReturn(modifyMenu);

        String requestJson1 = objectWriter.writeValueAsString(menu);
        String requestJson2 = objectWriter.writeValueAsString(modifyMenu);

        mockMvc.perform(post(URI_MENU)
                .contentType(MediaType.APPLICATION_JSON).content(requestJson1))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("branch.name", Matchers.is(NAME_KRAL_BURGER_SISLI)))
                .andExpect(jsonPath("id", Matchers.is(1)));

        mockMvc.perform(put(URI_MENU)
                .contentType(MediaType.APPLICATION_JSON).content(requestJson2))
                .andExpect(status().isOk())
                .andExpect(jsonPath("branch.name", Matchers.is(NAME_KRAL_BURGER_BESIKTAS)))
                .andExpect(jsonPath("id", Matchers.is(1)));
    }

    @Test
    public void whenDeleteMenu_thenReturnMenu() throws Exception {

        Branch branch = Branch.builder().name(NAME_KRAL_BURGER_SISLI).build();

        Meal meal = Meal.builder().build();

        Menu menu = Menu.builder()
                .id(1L)
                .branch(branch)
                .mealList(Arrays.asList(meal))
                .build();

        Mockito.when(menuService.deleteById(1L)).thenReturn(menu);

        String requestJson = objectWriter.writeValueAsString(menu);

        mockMvc.perform(delete(URI_MENU + "/1")
                .contentType(MediaType.APPLICATION_JSON).content(requestJson))
                .andExpect(status().isOk());
    }


}
