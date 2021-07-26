package com.finartz.restaurantapp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.finartz.restaurantapp.model.User;
import com.finartz.restaurantapp.model.enumerated.Role;
import com.finartz.restaurantapp.service.UserService;
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
@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Test
    public void whenGetAllUser_thenReturnUser() throws Exception {

        User user = User.builder()
                .id(1L)
                .name("Ali Akay")
                .email("ali@gmail.com")
                .password("ali1212")
                .role(Role.USER)
                .build();

        List<User> userList = Arrays.asList(user);

        Mockito.when(userService.getAll()).thenReturn(userList);

        mockMvc.perform(get("/user")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(1)))
                .andExpect(jsonPath("$[0].email", Matchers.is("ali@gmail.com")));

    }

    @Test
    public void whenGetUserById_thenReturnUser() throws Exception {

        User user = User.builder()
                .id(1L)
                .name("Ali Akay")
                .email("ali@gmail.com")
                .password("ali1212")
                .role(Role.USER)
                .build();

        Mockito.when(userService.getById(1L)).thenReturn(user);

        mockMvc.perform(get("/user/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("email", Matchers.is("ali@gmail.com")));

    }

    @Test
    public void whenCreateNewUser_thenReturnUser() throws Exception {

        User user = User.builder()
                .id(1L)
                .name("Ali Akay")
                .email("ali@gmail.com")
                .password("ali1212")
                .role(Role.USER)
                .build();

        Mockito.when(userService.create(user)).thenReturn(user);

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = ow.writeValueAsString(user);

        mockMvc.perform(post("/user")
                .contentType(MediaType.APPLICATION_JSON).content(requestJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("email", Matchers.is("ali@gmail.com")));
    }

    @Test
    public void whenUpdateUser_thenReturnUser() throws Exception {

        User user = User.builder()
                .id(1L)
                .name("Ali Akay")
                .email("ali@gmail.com")
                .password("ali1212")
                .role(Role.USER)
                .build();

        user.setPassword("ali121212");

        Mockito.when(userService.update(user)).thenReturn(user);

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = ow.writeValueAsString(user);

        mockMvc.perform(put("/user")
                .contentType(MediaType.APPLICATION_JSON).content(requestJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("password", Matchers.is("ali121212")));
    }

    @Test
    public void whenDeleteUser_thenReturnUser() throws Exception {

        User user = User.builder()
                .id(1L)
                .name("Ali Akay")
                .email("ali@gmail.com")
                .password("ali1212")
                .role(Role.USER)
                .build();

        Mockito.when(userService.deleteById(1L)).thenReturn(user);

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = ow.writeValueAsString(user);

        mockMvc.perform(delete("/user/1")
                .contentType(MediaType.APPLICATION_JSON).content(requestJson))
                .andExpect(status().isOk());
    }


}
