package com.finartz.restaurantapp.service.impl;

import com.finartz.restaurantapp.model.Item;
import com.finartz.restaurantapp.repository.ItemRepository;
import com.finartz.restaurantapp.service.ItemService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;

    public ItemServiceImpl(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Override
    public Item create(Item item){
        Item save = itemRepository.save(item);
        return save;
    }

    @Override
    public List<Item> getAll(){
        List<Item> items = itemRepository.findAll();
        return items;
    }

    @Override
    public Item getById(Long id){
        Item item = itemRepository.getById(id);
        return item;
    }

    @Override
    public Item update(Item item){
        Item foundItem = itemRepository.getById(item.getId());

        if (item.getName() != null)
            foundItem.setName(item.getName());
        if (item.getUnitType() != null)
            foundItem.setUnitType(item.getUnitType());
        if (item.getMealList() != null)
            foundItem.setMealList(item.getMealList());

        return itemRepository.save(item);

    }

    @Override
    public Item deleteById(Long id){
        Item item = itemRepository.getById(id);
        if (item != null) {
            itemRepository.deleteById(id);
            return item;
        }
        return item;
    }
}
