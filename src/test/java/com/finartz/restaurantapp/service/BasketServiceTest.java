package com.finartz.restaurantapp.service;

import com.finartz.restaurantapp.model.Basket;
import com.finartz.restaurantapp.repository.BasketRepository;
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
public class BasketServiceTest {

    @InjectMocks
    private BasketService basketService;

    @Mock
    private BasketRepository basketRepository;


    @Test
    public void whenFetchAll_thenReturnAllBasket() {
        Basket basket1 = Basket.builder().id(1l).totalPrice(110.99).build();
        Basket basket2 = Basket.builder().id(2l).totalPrice(115.00).build();
        List<Basket> basketList = Arrays.asList(basket1, basket2);

        Mockito.when(basketRepository.findAll()).thenReturn(basketList);

        List<Basket> fetchedList = basketService.getAll();

        assertEquals(basketList.size(), fetchedList.size());
    }

    @Test
    public void whenFetchById_thenReturnBasket() {
        Basket basket = Basket.builder().totalPrice(110.99).build();

        Mockito.when(basketRepository.getById(1L)).thenReturn(basket);

        Basket fetchedBasket = basketService.getById(1L);

        assertEquals(basket.getId(), fetchedBasket.getId());
    }

    @Test
    public void whenAddBasket_thenReturnSavedBasket() {
        Basket basket = Basket.builder().totalPrice(110.99).build();

        Mockito.doReturn(basket).when(basketRepository).save(basket);

        Basket returnedBasket = basketService.create(basket);

        assertEquals(basket.getTotalPrice(), returnedBasket.getTotalPrice());
    }

    @Test
    public void whenUpdateBasket_thenReturnUpdatedBasket(){
        Basket basket = Basket.builder().totalPrice(110.99).build();

        Mockito.when(basketRepository.save(basket)).thenReturn(basket);

        Basket updatedBasket = basketService.update(basket);

        assertEquals(basket , updatedBasket);

    }

}
