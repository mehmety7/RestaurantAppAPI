package com.finartz.restaurantapp.service;

import com.finartz.restaurantapp.model.Orders;
import com.finartz.restaurantapp.repository.OrdersRepository;
import com.finartz.restaurantapp.service.impl.OrdersServiceImpl;
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
public class OrdersServiceTest {

    private static final Double PRICE_100 = 100.00;
    private static final Double PRICE_200 = 200.00;

    @InjectMocks
    private OrdersServiceImpl ordersService;

    @Mock
    private OrdersRepository ordersRepository;


    @Test
    public void whenFetchAll_thenReturnAllOrders() {
        Orders orders1 = Orders.builder().id(1l).totalPrice(PRICE_100).build();
        Orders orders2 = Orders.builder().id(2l).totalPrice(PRICE_100).build();
        List<Orders> ordersList = Arrays.asList(orders1, orders2);

        Mockito.when(ordersRepository.findAll()).thenReturn(ordersList);

        List<Orders> fetchedList = ordersService.getAll();

        assertEquals(ordersList.size(), fetchedList.size());
    }

    @Test
    public void whenFetchById_thenReturnOrders() {
        Orders orders = Orders.builder().totalPrice(PRICE_100).build();

        Mockito.when(ordersRepository.getById(1L)).thenReturn(orders);

        Orders fetchedOrders = ordersService.getById(1L);

        assertEquals(orders.getId(), fetchedOrders.getId());
    }

    @Test
    public void whenAddOrders_thenReturnSavedOrders() {
        Orders orders = Orders.builder().totalPrice(PRICE_100).build();

        Mockito.doReturn(orders).when(ordersRepository).save(orders);

        Orders returnedOrders = ordersService.create(orders);

        assertEquals(orders.getTotalPrice(), returnedOrders.getTotalPrice());
    }

    @Test
    public void whenUpdateOrders_thenReturnUpdatedOrders(){
        Orders foundOrders = Orders.builder().id(1l).totalPrice(PRICE_100).build();
        Orders modifyOrders = Orders.builder().id(1l).totalPrice(PRICE_200).build();

        Mockito.when(ordersRepository.getById(1l)).thenReturn(foundOrders);
        Mockito.when(ordersRepository.save(modifyOrders)).thenReturn(modifyOrders);

        Orders updatedOrders = ordersService.update(modifyOrders);

        Assertions.assertNotEquals(updatedOrders.getTotalPrice(), PRICE_100);
        Assertions.assertEquals(updatedOrders.getTotalPrice(), PRICE_200);

    }

}