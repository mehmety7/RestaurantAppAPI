package com.finartz.restaurantapp.service;

import com.finartz.restaurantapp.model.Item;

import java.util.List;

public interface ItemService {

    public Item create(Item item);

    public List<Item> getAll();

    public Item getById(Long id);

    public Item update(Item item);

    public Item deleteById(Long id);
}
