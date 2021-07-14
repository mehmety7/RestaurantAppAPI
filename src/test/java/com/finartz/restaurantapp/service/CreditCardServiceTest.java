package com.finartz.restaurantapp.service;

import com.finartz.restaurantapp.model.CreditCard;
import com.finartz.restaurantapp.repository.CreditCardRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class CreditCardServiceTest {

    @InjectMocks
    private CreditCardService creditCardService;

    @Mock
    private CreditCardRepository creditCardRepository;


    @Test
    public void whenFetchAll_thenReturnAllCreditCard() {
        CreditCard creditCard1 = CreditCard.builder().id(1l).name("Garanti Mehmet").build();
        CreditCard creditCard2 = CreditCard.builder().id(2l).name("YapıKredi Mehmet").build();
        List<CreditCard> creditCardList = Arrays.asList(creditCard1, creditCard2);

        Mockito.when(creditCardRepository.findAll()).thenReturn(creditCardList);

        List<CreditCard> fetchedList = creditCardService.getAll();

        assertEquals(creditCardList.size(), fetchedList.size());
    }

    @Test
    public void whenFetchById_thenReturnCreditCard() {
        CreditCard creditCard = CreditCard.builder().name("Garanti Mehmet").build();

        Mockito.when(creditCardRepository.getById(1L)).thenReturn(creditCard);

        CreditCard fetchedCreditCard = creditCardService.getById(1L);

        assertEquals(creditCard.getId(), fetchedCreditCard.getId());
    }

    @Test
    public void whenAddCreditCard_thenReturnSavedCreditCard() {
        CreditCard creditCard = CreditCard.builder().name("Garanti Mehmet").build();

        Mockito.doReturn(creditCard).when(creditCardRepository).save(creditCard);

        CreditCard returnedCreditCard = creditCardService.create(creditCard);

        assertEquals(creditCard.getName(), returnedCreditCard.getName());
    }

}