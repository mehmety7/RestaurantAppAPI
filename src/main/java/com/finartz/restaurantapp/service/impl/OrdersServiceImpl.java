package com.finartz.restaurantapp.service.impl;

import com.finartz.restaurantapp.model.Orders;
import com.finartz.restaurantapp.repository.OrdersRepository;
import com.finartz.restaurantapp.service.MenuService;
import com.finartz.restaurantapp.service.OrdersService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrdersServiceImpl implements OrdersService {

    private final OrdersRepository ordersRepository;

    public OrdersServiceImpl(OrdersRepository ordersRepository) {
        this.ordersRepository = ordersRepository;
    }

    @Override
    public Orders create(Orders orders){
        return ordersRepository.save(orders);
    }

    @Override
    public List<Orders> getAll(){
        return ordersRepository.findAll();
    }

    @Override
    public Orders getById(Long id){
        return ordersRepository.getById(id);
    }

    @Override
    public Orders update(Orders orders){
        Orders foundOrders = ordersRepository.getById(orders.getId());

        if (orders.getTotalPrice() != null)
            foundOrders.setTotalPrice(orders.getTotalPrice());
        if (orders.getBasket() != null)
            foundOrders.setBasket(orders.getBasket());
        if (orders.getDate() != null)
            foundOrders.setDate(orders.getDate());
        if (orders.getCreditCard() != null)
            foundOrders.setCreditCard(orders.getCreditCard());

        return ordersRepository.save(foundOrders);
    }

    @Override
    public Orders deleteById(Long id){
        Orders orders = ordersRepository.getById(id);
        if (orders != null) {
            ordersRepository.deleteById(id);
            return orders;
        }
        return orders;
    }
}
