package com.finartz.restaurantapp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.finartz.restaurantapp.model.CreditCard;
import com.finartz.restaurantapp.model.User;
import com.finartz.restaurantapp.service.CreditCardService;
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
@WebMvcTest(CreditCardController.class)
public class CreditCardControllerTest {

    private static final String URI_CCARD = "/credit-card";
    private static final String CREDIT_CARD_GARANTI = "Garanti";
    private static final String CREDIT_CARD_FINANSBANK = "Finansbank";
    private static final String CREDIT_CARD_NO = "202020";
    private static final Integer EXP_MONTH_10 = 10;
    private static final Integer EXP_YEAR_24 = 24;
    private static final Integer CCV_966 = 966;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CreditCardService creditCardService;

    private ObjectWriter objectWriter;

    @Before
    public void init() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        objectWriter = mapper.writer().withDefaultPrettyPrinter();
    }

    @Test
    public void whenGetAllCreditCard_thenReturnCreditCard() throws Exception {

        User user = User.builder().build();

        CreditCard creditCard = CreditCard.builder()
                .id(1L)
                .name(CREDIT_CARD_GARANTI)
                .cardNo(CREDIT_CARD_NO)
                .expMonth(EXP_MONTH_10)
                .expYear(EXP_YEAR_24)
                .ccv(CCV_966)
                .user(user)
                .build();

        List<CreditCard> creditCardList = Arrays.asList(creditCard);

        Mockito.when(creditCardService.getAll()).thenReturn(creditCardList);

        mockMvc.perform(get(URI_CCARD)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(1)))
                .andExpect(jsonPath("$[0].name", Matchers.is(CREDIT_CARD_GARANTI)));

    }

    @Test
    public void whenGetCreditCardById_thenReturnCreditCard() throws Exception {

        User user = User.builder().build();

        CreditCard creditCard = CreditCard.builder()
                .id(1L)
                .name(CREDIT_CARD_GARANTI)
                .cardNo(CREDIT_CARD_NO)
                .expMonth(EXP_MONTH_10)
                .expYear(EXP_YEAR_24)
                .ccv(CCV_966)
                .user(user)
                .build();

        Mockito.when(creditCardService.getById(1L)).thenReturn(creditCard);

        mockMvc.perform(get(URI_CCARD + "/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("name", Matchers.is(CREDIT_CARD_GARANTI)));

    }

    @Test
    public void whenCreateNewCreditCard_thenReturnCreditCard() throws Exception {

        User user = User.builder().build();

        CreditCard creditCard = CreditCard.builder()
                .id(1L)
                .name(CREDIT_CARD_GARANTI)
                .cardNo(CREDIT_CARD_NO)
                .expMonth(EXP_MONTH_10)
                .expYear(EXP_YEAR_24)
                .ccv(CCV_966)
                .user(user)
                .build();

        Mockito.when(creditCardService.create(creditCard)).thenReturn(creditCard);

        String requestJson = objectWriter.writeValueAsString(creditCard);

        mockMvc.perform(post(URI_CCARD)
                .contentType(MediaType.APPLICATION_JSON).content(requestJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("name", Matchers.is(CREDIT_CARD_GARANTI)));
    }

    @Test
    public void whenUpdateCreditCard_thenReturnCreditCard() throws Exception {

        User user = User.builder().build();

        CreditCard creditCard = CreditCard.builder()
                .id(1L)
                .name(CREDIT_CARD_GARANTI)
                .cardNo(CREDIT_CARD_NO)
                .expMonth(EXP_MONTH_10)
                .expYear(EXP_YEAR_24)
                .ccv(CCV_966)
                .user(user)
                .build();

        CreditCard modifyCreditCard = CreditCard.builder()
                .id(1L)
                .name(CREDIT_CARD_FINANSBANK)
                .cardNo(CREDIT_CARD_NO)
                .expMonth(EXP_MONTH_10)
                .expYear(EXP_YEAR_24)
                .ccv(CCV_966)
                .user(user)
                .build();

        Mockito.when(creditCardService.create(creditCard)).thenReturn(creditCard);
        Mockito.when(creditCardService.update(modifyCreditCard)).thenReturn(modifyCreditCard);

        String requestJson1 = objectWriter.writeValueAsString(creditCard);
        String requestJson2 = objectWriter.writeValueAsString(modifyCreditCard);

        mockMvc.perform(post(URI_CCARD)
                .contentType(MediaType.APPLICATION_JSON).content(requestJson1))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("name", Matchers.is(CREDIT_CARD_GARANTI)))
                .andExpect(jsonPath("id", Matchers.is(1)));

        mockMvc.perform(put(URI_CCARD)
                .contentType(MediaType.APPLICATION_JSON).content(requestJson2))
                .andExpect(status().isOk())
                .andExpect(jsonPath("name", Matchers.is(CREDIT_CARD_FINANSBANK)))
                .andExpect(jsonPath("id", Matchers.is(1)));
    }

    @Test
    public void whenDeleteCreditCard_thenReturnCreditCard() throws Exception {

        User user = User.builder().build();

        CreditCard creditCard = CreditCard.builder()
                .id(1L)
                .name(CREDIT_CARD_GARANTI)
                .cardNo(URI_CCARD)
                .expMonth(EXP_MONTH_10)
                .expYear(EXP_YEAR_24)
                .ccv(CCV_966)
                .user(user)
                .build();

        Mockito.when(creditCardService.deleteById(1L)).thenReturn(creditCard);

        String requestJson = objectWriter.writeValueAsString(creditCard);

        mockMvc.perform(delete(URI_CCARD + "/1")
                .contentType(MediaType.APPLICATION_JSON).content(requestJson))
                .andExpect(status().isOk());
    }


}
