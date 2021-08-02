package com.finartz.restaurantapp.service;

import com.finartz.restaurantapp.model.Basket;

import java.util.List;

public interface BasketService {

    public Basket create(Basket basket);

    public List<Basket> getAll();

    public Basket getById(Long id);

    public Basket update(Basket basket);

    public Basket deleteById(Long id);

}
