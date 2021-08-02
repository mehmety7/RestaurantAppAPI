package com.finartz.restaurantapp.service;

import com.finartz.restaurantapp.model.Orders;

import java.util.List;

public interface OrdersService {

    public Orders create(Orders orders);

    public List<Orders> getAll();

    public Orders getById(Long id);

    public Orders update(Orders orders);

    public Orders deleteById(Long id);

}
