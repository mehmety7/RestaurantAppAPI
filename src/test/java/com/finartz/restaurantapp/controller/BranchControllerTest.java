package com.finartz.restaurantapp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.finartz.restaurantapp.model.*;
import com.finartz.restaurantapp.model.enumerated.Status;
import com.finartz.restaurantapp.service.BranchService;
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
@WebMvcTest(BranchController.class)
public class BranchControllerTest {

    private static final String URI_BRANCH = "/branch";
    private static final String NAME_KRAL_BURGER = "Kral Burger";
    private static final String NAME_KRAL_SISLI = "Kral Burger Şişli";
    private static final String NAME_KRAL_MECIDIYEKOY = "Kral Burger Mecidiyeköy";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BranchService branchService;

    private ObjectWriter objectWriter;

    @Before
    public void init() {
        // to-do : https://stackoverflow.com/questions/20504399/testing-springs-requestbody-using-spring-mockmvc
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        objectWriter = mapper.writer().withDefaultPrettyPrinter();
    }

    @Test
    public void whenGetAllBranch_thenReturnBranch() throws Exception {

        Restaurant restaurant = Restaurant.builder()
                .id(1l)
                .name(NAME_KRAL_BURGER)
                .status(Status.APPROVED)
                .build();

        Address address = Address.builder().build();

        Menu menu = Menu.builder().build();

        Branch branch = Branch.builder()
                .address(address)
                .restaurant(restaurant)
                .menu(menu)
                .name(NAME_KRAL_SISLI)
                .id(1L)
                .status(Status.WAITING)
                .build();

        List<Branch> branchList = Arrays.asList(branch);

        Mockito.when(branchService.getAll()).thenReturn(branchList);

        mockMvc.perform(get(URI_BRANCH)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(1)))
                .andExpect(jsonPath("$[0].status", Matchers.is("WAITING")));
    }

    @Test
    public void whenGetByBranchId_thenReturnBranch() throws Exception {

        Restaurant restaurant = Restaurant.builder()
                .id(1l)
                .name(NAME_KRAL_BURGER)
                .status(Status.APPROVED)
                .build();

        Address address = Address.builder().build();

        Menu menu = Menu.builder().build();

        Branch branch = Branch.builder()
                .address(address)
                .restaurant(restaurant)
                .menu(menu)
                .name(NAME_KRAL_SISLI)
                .id(1L)
                .status(Status.WAITING)
                .build();

        Mockito.when(branchService.getById(1L)).thenReturn(branch);

        mockMvc.perform(get(URI_BRANCH + "/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("name", Matchers.is(NAME_KRAL_SISLI)));

    }

    @Test
    public void whenCreateNewBranch_thenReturnCreated() throws Exception {

        Restaurant restaurant = Restaurant.builder()
                .id(1l)
                .name(NAME_KRAL_BURGER)
                .status(Status.APPROVED)
                .build();

        Address address = Address.builder().build();

        Menu menu = Menu.builder().build();

        Branch branch = Branch.builder()
                .address(address)
                .restaurant(restaurant)
                .menu(menu)
                .name(NAME_KRAL_SISLI)
                .id(1L)
                .status(Status.WAITING)
                .build();


        Mockito.when(branchService.create(branch)).thenReturn(branch);

        String requestJson = objectWriter.writeValueAsString(branch);

        mockMvc.perform(post(URI_BRANCH)
                .contentType(MediaType.APPLICATION_JSON).content(requestJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("name", Matchers.is(NAME_KRAL_SISLI)));

    }

    @Test
    public void whenUpdateExistsBranch_thenReturnUpdated() throws Exception {

        Restaurant restaurant = Restaurant.builder()
                .id(1l)
                .name(NAME_KRAL_BURGER)
                .status(Status.APPROVED)
                .build();

        Address address = Address.builder().build();

        Menu menu = Menu.builder().build();

        Branch branch = Branch.builder()
                .address(address)
                .restaurant(restaurant)
                .menu(menu)
                .id(1L)
                .name(NAME_KRAL_SISLI)
                .status(Status.WAITING)
                .build();

        Branch modifyBranch = Branch.builder().address(address).restaurant(restaurant).name(NAME_KRAL_MECIDIYEKOY).id(1l).build();

        Mockito.when(branchService.create(branch)).thenReturn(branch);
        Mockito.when(branchService.update(modifyBranch)).thenReturn(modifyBranch);

        String requestJson1 = objectWriter.writeValueAsString(branch);
        String requestJson2 = objectWriter.writeValueAsString(modifyBranch);

        mockMvc.perform(post(URI_BRANCH)
                .contentType(MediaType.APPLICATION_JSON).content(requestJson1))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("name", Matchers.is(NAME_KRAL_SISLI)))
                .andExpect(jsonPath("id", Matchers.is(1)));

        mockMvc.perform(put(URI_BRANCH)
                .contentType(MediaType.APPLICATION_JSON).content(requestJson2))
                .andExpect(status().isOk())
                .andExpect(jsonPath("name", Matchers.is(NAME_KRAL_MECIDIYEKOY)))
                .andExpect(jsonPath("id", Matchers.is(1)));

    }

    @Test
    public void whenDeleteExistsBranch_thenReturnOk() throws Exception {

        Restaurant restaurant = Restaurant.builder()
                .id(1l)
                .name(NAME_KRAL_BURGER)
                .status(Status.APPROVED)
                .build();

        Address address = Address.builder().build();

        Menu menu = Menu.builder().build();

        Branch branch = Branch.builder()
                .address(address)
                .restaurant(restaurant)
                .menu(menu)
                .name(NAME_KRAL_SISLI)
                .id(1L)
                .status(Status.WAITING)
                .build();


        Mockito.when(branchService.deleteById(1L)).thenReturn(branch);

        mockMvc.perform(delete(URI_BRANCH + "/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void whenGetRestaurantIsWaiting_thenReturnRestaurant() throws Exception {

        User user = User.builder().build();

        Branch branch = Branch.builder().status(Status.WAITING).build();

        List<Branch> branchList = Arrays.asList(branch);

        Mockito.when(branchService.findByStatus(Status.WAITING)).thenReturn(branchList);

        mockMvc.perform(get(URI_BRANCH + "/waiting")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(1)));

    }

}
