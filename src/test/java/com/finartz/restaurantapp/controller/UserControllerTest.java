package com.finartz.restaurantapp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.finartz.restaurantapp.model.User;
import com.finartz.restaurantapp.service.UserService;
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
import org.springframework.security.core.userdetails.UserDetailsService;
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


    private static final String URI_USER = "/user";
    private static final String NAME_ALI_AKAY = "Ali Akay";
    private static final String EMAIL_ALI = "ali@gmail.com";
    private static final String PASSWORD_ALI1212 = "ali1212";
    private static final String PASSWORD_ALI123 = "ali123";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

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
    public void whenGetAllUser_thenReturnUser() throws Exception {

        User user = User.builder()
                .id(1L)
                .name(NAME_ALI_AKAY)
                .email(EMAIL_ALI)
                .password(PASSWORD_ALI1212)
                .build();

        List<User> userList = Arrays.asList(user);

        Mockito.when(userService.getAll()).thenReturn(userList);

        mockMvc.perform(get(URI_USER)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(1)))
                .andExpect(jsonPath("$[0].email", Matchers.is(EMAIL_ALI)));

    }

    @Test
    public void whenGetUserById_thenReturnUser() throws Exception {

        User user = User.builder()
                .id(1L)
                .name(NAME_ALI_AKAY)
                .email(EMAIL_ALI)
                .password(PASSWORD_ALI1212)
                .build();

        Mockito.when(userService.getById(1L)).thenReturn(user);

        mockMvc.perform(get("/user/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("email", Matchers.is(EMAIL_ALI)));

    }

    @Test
    public void whenCreateNewUser_thenReturnUser() throws Exception {

        User user = User.builder()
                .id(1L)
                .name(NAME_ALI_AKAY)
                .email(EMAIL_ALI)
                .password(PASSWORD_ALI1212)
                .build();

        Mockito.when(userService.create(user)).thenReturn(user);

        String requestJson = objectWriter.writeValueAsString(user);

        mockMvc.perform(post(URI_USER)
                .contentType(MediaType.APPLICATION_JSON).content(requestJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("email", Matchers.is(EMAIL_ALI)));
    }

    @Test
    public void whenUpdateUser_thenReturnUser() throws Exception {

        User user = User.builder()
                .id(1L)
                .name(NAME_ALI_AKAY)
                .email(EMAIL_ALI)
                .password(PASSWORD_ALI1212)
                .build();

        User modifyUser = User.builder()
                .id(1L)
                .name(NAME_ALI_AKAY)
                .email(EMAIL_ALI)
                .password(PASSWORD_ALI123)
                .build();

        Mockito.when(userService.create(user)).thenReturn(user);
        Mockito.when(userService.update(modifyUser)).thenReturn(modifyUser);

        String requestJson1 = objectWriter.writeValueAsString(user);
        String requestJson2 = objectWriter.writeValueAsString(modifyUser);

        mockMvc.perform(post(URI_USER)
                .contentType(MediaType.APPLICATION_JSON).content(requestJson1))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.password", Matchers.is(PASSWORD_ALI1212)))
                .andExpect(jsonPath("$.id", Matchers.is(1)));

        mockMvc.perform(put(URI_USER)
                .contentType(MediaType.APPLICATION_JSON).content(requestJson2))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.password", Matchers.is(PASSWORD_ALI123)))
                .andExpect(jsonPath("$.id", Matchers.is(1)));
    }

    @Test
    public void whenDeleteUser_thenReturnUser() throws Exception {

        User user = User.builder()
                .id(1L)
                .name(NAME_ALI_AKAY)
                .email(EMAIL_ALI)
                .password(PASSWORD_ALI1212)
                .build();

        Mockito.when(userService.deleteById(1L)).thenReturn(user);

        String requestJson = objectWriter.writeValueAsString(user);

        mockMvc.perform(delete(URI_USER + "/1")
                .contentType(MediaType.APPLICATION_JSON).content(requestJson))
                .andExpect(status().isOk());
    }

    @Test
    public void whenFindByEmail_thenReturnUser() throws Exception {

        User user = User.builder().email(EMAIL_ALI).build();

        Mockito.when(userService.findByEmail(EMAIL_ALI)).thenReturn(user);

        mockMvc.perform(get(URI_USER + "/email/" + user.getEmail())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("email", Matchers.is(EMAIL_ALI)));
    }

}
