package com.finartz.restaurantapp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.finartz.restaurantapp.model.dto.UserDto;
import com.finartz.restaurantapp.model.enumerated.Role;
import com.finartz.restaurantapp.model.request.create.AddressCreateRequest;
import com.finartz.restaurantapp.model.request.create.UserCreateRequest;
import com.finartz.restaurantapp.service.TokenService;
import com.finartz.restaurantapp.service.UserService;
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
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collections;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@ExtendWith(SpringExtension.class)
@WebMvcTest(UserController.class)
@AutoConfigureMockMvc(addFilters = false)
public class UserControllerTest {

    private static final String URI_USER = "/user";
    private static final String NAME_ALI_AKAY = "Ali Akay";
    private static final String EMAIL_ALI = "ali@gmail.com";
    private static final String PASSWORD_ALI1212 = "ali1212";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @MockBean
    private TokenService tokenService;

    private ObjectWriter objectWriter;

    @Before
    public void init() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        objectWriter = mapper.writer().withDefaultPrettyPrinter();
    }

    @Test
    public void whenGetUserById_thenReturnUser() throws Exception {

        UserDto user = UserDto.builder()
                .id(1L)
                .name(NAME_ALI_AKAY)
                .email(EMAIL_ALI)
                .build();

        Mockito.when(userService.getUser(1L)).thenReturn(user);

        mockMvc.perform(get(URI_USER + "/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("email", Matchers.is(EMAIL_ALI)));

    }

    @Test
    public void whenCreateNewUser_thenReturnUser() throws Exception {

        UserDto user = UserDto.builder()
                .id(1L)
                .name(NAME_ALI_AKAY)
                .email(EMAIL_ALI)
                .build();

        UserCreateRequest userCreateRequest = UserCreateRequest
                .builder()
                .name(NAME_ALI_AKAY)
                .email(EMAIL_ALI)
                .password(PASSWORD_ALI1212)
                .roles(Collections.singletonList(Role.USER))
                .addressCreateRequest(AddressCreateRequest.builder().build())
                .build();

        Mockito.when(userService.createUser(userCreateRequest)).thenReturn(user);

        String requestJson = objectWriter.writeValueAsString(userCreateRequest);

        mockMvc.perform(post(URI_USER)
                .contentType(MediaType.APPLICATION_JSON).content(requestJson))
                .andExpect(status().isCreated());
    }

    @Test
    public void whenGiveValidRefreshToken_thenReturnNewTokensWithRefreshToken() throws Exception {

        String refreshToken = "";
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);

        Mockito.when(request.getHeader(HttpHeaders.AUTHORIZATION)).thenReturn("Bearer " + refreshToken);

        Mockito.doNothing().when(tokenService).refreshToken(request, response);

        mockMvc.perform(get(URI_USER + "/refresh-token")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

}


