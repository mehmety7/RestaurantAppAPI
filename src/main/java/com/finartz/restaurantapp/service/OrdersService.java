package com.finartz.restaurantapp.service;

import com.finartz.restaurantapp.model.Orders;
import com.finartz.restaurantapp.repository.OrdersRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrdersService {

    private final OrdersRepository ordersRepository;

    public OrdersService(OrdersRepository ordersRepository) {
        this.ordersRepository = ordersRepository;
    }

    public Orders create(Orders orders){
        return ordersRepository.save(orders);
    }

    public List<Orders> getAll(){
        return ordersRepository.findAll();
    }

    public Orders getById(Long id){
        return ordersRepository.getById(id);
    }

    public Orders update(Orders orders){
        Orders update = ordersRepository.getById(orders.getId());
        if(update != null) {
            ordersRepository.save(orders);
            return update;
        }
        return orders;
    }

    public Orders deleteById(Long id){
        Orders orders = ordersRepository.getById(id);
        if (orders != null) {
            ordersRepository.deleteById(id);
            return orders;
        }
        return orders;
    }
}
