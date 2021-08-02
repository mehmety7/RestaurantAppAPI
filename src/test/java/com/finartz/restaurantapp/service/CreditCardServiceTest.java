package com.finartz.restaurantapp.service;

import com.finartz.restaurantapp.model.CreditCard;
import com.finartz.restaurantapp.repository.CreditCardRepository;
import com.finartz.restaurantapp.service.impl.CreditCardServiceImpl;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
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

    private static final String NAME_GARANTI = "Garanti - Mehmet";
    private static final String NAME_YAPI_KREDI = "YapıKredi - Mehmet";

    @InjectMocks
    private CreditCardServiceImpl creditCardService;

    @Mock
    private CreditCardRepository creditCardRepository;


    @Test
    public void whenFetchAll_thenReturnAllCreditCard() {
        CreditCard creditCard1 = CreditCard.builder().id(1l).name(NAME_GARANTI).build();
        CreditCard creditCard2 = CreditCard.builder().id(2l).name(NAME_YAPI_KREDI).build();
        List<CreditCard> creditCardList = Arrays.asList(creditCard1, creditCard2);

        Mockito.when(creditCardRepository.findAll()).thenReturn(creditCardList);

        List<CreditCard> fetchedList = creditCardService.getAll();

        assertEquals(creditCardList.size(), fetchedList.size());
    }

    @Test
    public void whenFetchById_thenReturnCreditCard() {
        CreditCard creditCard = CreditCard.builder().name(NAME_GARANTI).build();

        Mockito.when(creditCardRepository.getById(1L)).thenReturn(creditCard);

        CreditCard fetchedCreditCard = creditCardService.getById(1L);

        assertEquals(creditCard.getId(), fetchedCreditCard.getId());
    }

    @Test
    public void whenAddCreditCard_thenReturnSavedCreditCard() {
        CreditCard creditCard = CreditCard.builder().name(NAME_GARANTI).build();

        Mockito.doReturn(creditCard).when(creditCardRepository).save(creditCard);

        CreditCard returnedCreditCard = creditCardService.create(creditCard);

        assertEquals(creditCard.getName(), returnedCreditCard.getName());
    }

    @Test
    public void whenUpdateCreditCard_thenReturnUpdatedCreditCard(){
        CreditCard foundCreditCard = CreditCard.builder().id(1l).name(NAME_GARANTI).build();
        CreditCard modifyCreditCard = CreditCard.builder().id(1l).name(NAME_YAPI_KREDI).build();

        Mockito.when(creditCardRepository.getById(1l)).thenReturn(foundCreditCard);
        Mockito.when(creditCardRepository.save(modifyCreditCard)).thenReturn(modifyCreditCard);

        CreditCard updatedCreditCard = creditCardService.update(modifyCreditCard);

        Assertions.assertNotEquals(updatedCreditCard.getName(), NAME_GARANTI);
        Assertions.assertEquals(updatedCreditCard.getName(), NAME_YAPI_KREDI);

    }

}