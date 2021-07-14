package com.finartz.restaurantapp.service;

import com.finartz.restaurantapp.model.Orders;
import com.finartz.restaurantapp.repository.OrdersRepository;
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
public class OrdersServiceTest {

    @InjectMocks
    private OrdersService ordersService;

    @Mock
    private OrdersRepository ordersRepository;


    @Test
    public void whenFetchAll_thenReturnAllOrders() {
        Orders orders1 = Orders.builder().id(1l).totalPrice(100.00).build();
        Orders orders2 = Orders.builder().id(2l).totalPrice(100.00).build();
        List<Orders> ordersList = Arrays.asList(orders1, orders2);

        Mockito.when(ordersRepository.findAll()).thenReturn(ordersList);

        List<Orders> fetchedList = ordersService.getAll();

        assertEquals(ordersList.size(), fetchedList.size());
    }

    @Test
    public void whenFetchById_thenReturnOrders() {
        Orders orders = Orders.builder().totalPrice(100.00).build();

        Mockito.when(ordersRepository.getById(1L)).thenReturn(orders);

        Orders fetchedOrders = ordersService.getById(1L);

        assertEquals(orders.getId(), fetchedOrders.getId());
    }

    @Test
    public void whenAddOrders_thenReturnSavedOrders() {
        Orders orders = Orders.builder().totalPrice(100.00).build();

        Mockito.doReturn(orders).when(ordersRepository).save(orders);

        Orders returnedOrders = ordersService.create(orders);

        assertEquals(orders.getTotalPrice(), returnedOrders.getTotalPrice());
    }

}