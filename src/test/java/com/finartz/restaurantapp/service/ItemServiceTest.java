package com.finartz.restaurantapp.service;

import com.finartz.restaurantapp.model.Item;
import com.finartz.restaurantapp.repository.ItemRepository;
import com.finartz.restaurantapp.service.impl.ItemServiceImpl;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class ItemServiceTest {

    private static final String NAME_HAMBURGER = "Hamburger";
    private static final String NAME_CHEESEBURGER = "Cheeseburger";

    @InjectMocks
    private ItemServiceImpl itemService;

    @Mock
    private ItemRepository itemRepository;


    @Test
    public void whenFetchAll_thenReturnAllItem() {
        Item item1 = Item.builder().id(1l).name(NAME_HAMBURGER).build();
        Item item2 = Item.builder().id(2l).name(NAME_CHEESEBURGER).build();
        List<Item> itemList = Arrays.asList(item1, item2);

        Mockito.when(itemRepository.findAll()).thenReturn(itemList);

        List<Item> fetchedList = itemService.getAll();

        assertEquals(itemList.size(), fetchedList.size());
    }

    @Test
    public void whenFetchById_thenReturnItem() {
        Item item = Item.builder().name(NAME_HAMBURGER).build();

        Mockito.when(itemRepository.getById(1L)).thenReturn(item);

        Item fetchedItem = itemService.getById(1L);

        assertEquals(item.getId(), fetchedItem.getId());
    }

    @Test
    public void whenAddItem_thenReturnSavedItem() {
        Item item = Item.builder().name(NAME_HAMBURGER).build();

        Mockito.doReturn(item).when(itemRepository).save(item);

        Item returnedItem = itemService.create(item);

        assertEquals(item.getName(), returnedItem.getName());
    }

    @Test
    public void whenUpdateItem_thenReturnUpdatedItem(){
        Item foundItem = Item.builder().id(1l).name(NAME_HAMBURGER).build();
        Item modifyItem = Item.builder().id(1l).name(NAME_CHEESEBURGER).build();

        Mockito.when(itemRepository.getById(1l)).thenReturn(foundItem);
        Mockito.when(itemRepository.save(modifyItem)).thenReturn(modifyItem);

        Item updatedItem = itemService.update(modifyItem);

        Assertions.assertNotEquals(updatedItem.getName(), NAME_HAMBURGER);
        Assertions.assertEquals(updatedItem.getName(), NAME_CHEESEBURGER);

    }

}