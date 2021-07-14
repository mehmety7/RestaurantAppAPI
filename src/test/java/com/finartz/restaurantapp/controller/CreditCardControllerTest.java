package com.finartz.restaurantapp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.finartz.restaurantapp.model.CreditCard;
import com.finartz.restaurantapp.model.User;
import com.finartz.restaurantapp.service.CreditCardService;
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
@WebMvcTest(CreditCardController.class)
public class CreditCardControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CreditCardService creditCardService;

    @Test
    public void whenGetAllCreditCard_thenReturnCreditCard() throws Exception {

        User user = User.builder().build();

        CreditCard creditCard = CreditCard.builder()
                .id(1L)
                .name("Ali Atik Garanti Bankası")
                .cardNo("101010202020")
                .expMonth(10)
                .expYear(24)
                .ccv(966)
                .user(user)
                .build();

        List<CreditCard> creditCardList = Arrays.asList(creditCard);

        Mockito.when(creditCardService.getAll()).thenReturn(creditCardList);

        mockMvc.perform(get("/creditcard")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(1)))
                .andExpect(jsonPath("$[0].name", Matchers.is("Ali Atik Garanti Bankası")));

    }

    @Test
    public void whenGetCreditCardById_thenReturnCreditCard() throws Exception {

        User user = User.builder().build();

        CreditCard creditCard = CreditCard.builder()
                .id(1L)
                .name("Ali Atik Garanti Bankası")
                .cardNo("101010202020")
                .expMonth(10)
                .expYear(24)
                .ccv(966)
                .user(user)
                .build();

        Mockito.when(creditCardService.getById(1L)).thenReturn(creditCard);

        mockMvc.perform(get("/creditcard/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("name", Matchers.is("Ali Atik Garanti Bankası")));

    }

    @Test
    public void whenCreateNewCreditCard_thenReturnCreditCard() throws Exception {

        User user = User.builder().build();

        CreditCard creditCard = CreditCard.builder()
                .id(1L)
                .name("Ali Atik Garanti Bankası")
                .cardNo("101010202020")
                .expMonth(10)
                .expYear(24)
                .ccv(966)
                .user(user)
                .build();

        Mockito.when(creditCardService.create(creditCard)).thenReturn(creditCard);

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = ow.writeValueAsString(creditCard);

        mockMvc.perform(post("/creditcard")
                .contentType(MediaType.APPLICATION_JSON).content(requestJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("name", Matchers.is("Ali Atik Garanti Bankası")));
    }

    @Test
    public void whenUpdateCreditCard_thenReturnCreditCard() throws Exception {

        User user = User.builder().build();

        CreditCard creditCard = CreditCard.builder()
                .id(1L)
                .name("Ali Atik Garanti Bankası")
                .cardNo("101010202020")
                .expMonth(10)
                .expYear(24)
                .ccv(966)
                .user(user)
                .build();

        creditCard.setName("Ali Atik Finansbank");

        Mockito.when(creditCardService.update(creditCard)).thenReturn(creditCard);

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = ow.writeValueAsString(creditCard);

        mockMvc.perform(put("/creditcard")
                .contentType(MediaType.APPLICATION_JSON).content(requestJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("name", Matchers.is("Ali Atik Finansbank")));
    }

    @Test
    public void whenDeleteCreditCard_thenReturnCreditCard() throws Exception {

        User user = User.builder().build();

        CreditCard creditCard = CreditCard.builder()
                .id(1L)
                .name("Ali Atik Garanti Bankası")
                .cardNo("101010202020")
                .expMonth(10)
                .expYear(24)
                .ccv(966)
                .user(user)
                .build();

        Mockito.when(creditCardService.deleteById(1L)).thenReturn(creditCard);

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = ow.writeValueAsString(creditCard);

        mockMvc.perform(delete("/creditcard/1")
                .contentType(MediaType.APPLICATION_JSON).content(requestJson))
                .andExpect(status().isOk());
    }


}
