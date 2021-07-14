package com.finartz.restaurantapp.service;

import com.finartz.restaurantapp.model.Item;
import com.finartz.restaurantapp.repository.ItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {

    private final ItemRepository itemRepository;

    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public Item create(Item item){
        Item save = itemRepository.save(item);
        return save;
    }

    public List<Item> getAll(){
        List<Item> items = itemRepository.findAll();
        return items;
    }

    public Item getById(Long id){
        Item item = itemRepository.getById(id);
        return item;
    }

    public Item update(Item item){
        Item update = itemRepository.getById(item.getId());
        if(update != null) {
            itemRepository.save(item);
            return update;
        }
        return null;
    }

    public Item deleteById(Long id){
        Item item = itemRepository.getById(id);
        if (item != null) {
            itemRepository.deleteById(id);
            return item;
        }
        return item;
    }
}
