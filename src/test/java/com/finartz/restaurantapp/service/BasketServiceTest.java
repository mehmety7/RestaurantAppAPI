package com.finartz.restaurantapp.service;

import com.finartz.restaurantapp.model.Basket;
import com.finartz.restaurantapp.repository.BasketRepository;
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
public class BasketServiceTest {

    private static final Double PRICE_100 = 100.0;
    private static final Double PRICE_200 = 200.0;

    @InjectMocks
    private BasketService basketService;

    @Mock
    private BasketRepository basketRepository;


    @Test
    public void whenFetchAll_thenReturnAllBasket() {
        Basket basket1 = Basket.builder().id(1l).totalPrice(PRICE_100).build();
        Basket basket2 = Basket.builder().id(2l).totalPrice(PRICE_200).build();
        List<Basket> basketList = Arrays.asList(basket1, basket2);

        Mockito.when(basketRepository.findAll()).thenReturn(basketList);

        List<Basket> fetchedList = basketService.getAll();

        assertEquals(basketList.size(), fetchedList.size());
    }

    @Test
    public void whenFetchById_thenReturnBasket() {
        Basket basket = Basket.builder().totalPrice(PRICE_100).build();

        Mockito.when(basketRepository.getById(1L)).thenReturn(basket);

        Basket fetchedBasket = basketService.getById(1L);

        assertEquals(basket.getId(), fetchedBasket.getId());
    }

    @Test
    public void whenAddBasket_thenReturnSavedBasket() {
        Basket basket = Basket.builder().totalPrice(PRICE_100).build();

        Mockito.doReturn(basket).when(basketRepository).save(basket);

        Basket returnedBasket = basketService.create(basket);

        assertEquals(basket.getTotalPrice(), returnedBasket.getTotalPrice());
    }

    @Test
    public void whenUpdateBasket_thenReturnUpdatedBasket(){
        Basket foundBasket = Basket.builder().id(1l).totalPrice(PRICE_100).build();
        Basket modifyBasket = Basket.builder().id(1l).totalPrice(PRICE_200).build();

        Mockito.when(basketRepository.getById(1l)).thenReturn(foundBasket);
        Mockito.when(basketRepository.save(modifyBasket)).thenReturn(modifyBasket);

        Basket updatedBasket = basketService.update(modifyBasket);

        Assertions.assertNotEquals(updatedBasket.getTotalPrice(), PRICE_100);
        Assertions.assertEquals(updatedBasket.getTotalPrice(), PRICE_200);

    }

}
