package com.finartz.restaurantapp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.finartz.restaurantapp.model.*;
import com.finartz.restaurantapp.model.enumerated.Status;
import com.finartz.restaurantapp.service.BranchService;
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
@WebMvcTest(BranchController.class)
public class BranchControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BranchService branchService;

    @Test
    public void whenGetAllBranch_thenReturnBranch() throws Exception {

        Restaurant restaurant = Restaurant.builder()
                .id(1l)
                .name("Kral Burger")
                .status(Status.APPROVED)
                .build();

        Address address = Address.builder().build();

        Menu menu = Menu.builder().build();

        Branch branch = Branch.builder()
                .address(address)
                .restaurant(restaurant)
                .menu(menu)
                .name("Kral Burger Şişli")
                .id(1L)
                .status(Status.WAITING)
                .build();

        List<Branch> branchList = Arrays.asList(branch);

        Mockito.when(branchService.getAll()).thenReturn(branchList);

        mockMvc.perform(get("/branch")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(1)))
                .andExpect(jsonPath("$[0].status", Matchers.is("WAITING")));
    }

    @Test
    public void whenGetByBranchId_thenReturnBranch() throws Exception {

        Restaurant restaurant = Restaurant.builder()
                .id(1l)
                .name("Kral Burger")
                .status(Status.APPROVED)
                .build();

        Address address = Address.builder().build();

        Menu menu = Menu.builder().build();

        Branch branch = Branch.builder()
                .address(address)
                .restaurant(restaurant)
                .menu(menu)
                .name("Kral Burger Şişli")
                .id(1L)
                .status(Status.WAITING)
                .build();

        Mockito.when(branchService.getById(1L)).thenReturn(branch);

        mockMvc.perform(get("/branch/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("name", Matchers.is("Kral Burger Şişli")));

    }

    @Test
    public void whenCreateNewBranch_thenReturnCreated() throws Exception {

        Restaurant restaurant = Restaurant.builder()
                .id(1l)
                .name("Kral Burger")
                .status(Status.APPROVED)
                .build();

        Address address = Address.builder().build();

        Menu menu = Menu.builder().build();

        Branch branch = Branch.builder()
                .address(address)
                .restaurant(restaurant)
                .menu(menu)
                .name("Kral Burger Şişli")
                .id(1L)
                .status(Status.WAITING)
                .build();


        Mockito.when(branchService.create(branch)).thenReturn(branch);

        // to-do : https://stackoverflow.com/questions/20504399/testing-springs-requestbody-using-spring-mockmvc
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = ow.writeValueAsString(branch);

        mockMvc.perform(post("/branch")
                .contentType(MediaType.APPLICATION_JSON).content(requestJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("name", Matchers.is("Kral Burger Şişli")));

    }

    @Test
    public void whenUpdateExistsBranch_thenReturnUpdated() throws Exception {

        Restaurant restaurant = Restaurant.builder()
                .id(1l)
                .name("Kral Burger")
                .status(Status.APPROVED)
                .build();

        Address address = Address.builder().build();

        Menu menu = Menu.builder().build();

        Branch branch = Branch.builder()
                .address(address)
                .restaurant(restaurant)
                .menu(menu)
                .id(1L)
                .name("Kral Burger Şişli")
                .status(Status.WAITING)
                .build();

        branch.setName("Kral Burger Mecidiyeköy");

        Mockito.when(branchService.update(branch)).thenReturn(branch);

        // to-do : https://stackoverflow.com/questions/20504399/testing-springs-requestbody-using-spring-mockmvc
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = ow.writeValueAsString(branch);

        mockMvc.perform(put("/branch")
                .contentType(MediaType.APPLICATION_JSON).content(requestJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("name", Matchers.is("Kral Burger Mecidiyeköy")));

    }

    @Test
    public void whenDeleteExistsBranch_thenReturnOk() throws Exception {

        Restaurant restaurant = Restaurant.builder()
                .id(1l)
                .name("Kral Burger")
                .status(Status.APPROVED)
                .build();

        Address address = Address.builder().build();

        Menu menu = Menu.builder().build();

        Branch branch = Branch.builder()
                .address(address)
                .restaurant(restaurant)
                .menu(menu)
                .name("Kral Burger Şişli")
                .id(1L)
                .status(Status.WAITING)
                .build();


        Mockito.when(branchService.deleteById(1L)).thenReturn(branch);

        mockMvc.perform(delete("/branch/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void whenGetRestaurantIsWaiting_thenReturnRestaurant() throws Exception {

        User user = User.builder().build();

        Branch branch = Branch.builder().status(Status.WAITING).build();

        List<Branch> branchList = Arrays.asList(branch);

        Mockito.when(branchService.findByStatus(Status.WAITING)).thenReturn(branchList);

        mockMvc.perform(get("/branch/waiting")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(1)));

    }

}
