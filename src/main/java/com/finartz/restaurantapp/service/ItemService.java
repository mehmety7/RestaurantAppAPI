package com.finartz.restaurantapp.service;

import com.finartz.restaurantapp.repository.ItemRepository;
import com.finartz.restaurantapp.service.base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemService extends BaseService {

    @Autowired
    private ItemRepository itemRepository;

    @Override
    public ItemRepository getRepository() {
        return itemRepository;
    }
}
