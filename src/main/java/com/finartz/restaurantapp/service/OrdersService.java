package com.finartz.restaurantapp.service;

import com.finartz.restaurantapp.model.Orders;
import com.finartz.restaurantapp.repository.OrdersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrdersService {

    @Autowired
    private OrdersRepository ordersRepository;

    public Orders create(Orders orders){
        Orders save = ordersRepository.save(orders);
        return save;
    }

    public List<Orders> getAll(){
        List<Orders> ordersList = ordersRepository.findAll();
        return ordersList;
    }

    public Orders getById(Long id){
        Orders orders = ordersRepository.getById(id);
        return orders;
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
