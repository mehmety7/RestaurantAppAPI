package com.finartz.restaurantapp.service;

import com.finartz.restaurantapp.model.Item;
import com.finartz.restaurantapp.repository.ItemRepository;
import org.junit.Test;
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

    @InjectMocks
    private ItemService itemService;

    @Mock
    private ItemRepository itemRepository;


    @Test
    public void whenFetchAll_thenReturnAllItem() {
        Item item1 = Item.builder().id(1l).name("Hamburger").build();
        Item item2 = Item.builder().id(2l).name("Cheeseburger").build();
        List<Item> itemList = Arrays.asList(item1, item2);

        Mockito.when(itemRepository.findAll()).thenReturn(itemList);

        List<Item> fetchedList = itemService.getAll();

        assertEquals(itemList.size(), fetchedList.size());
    }

    @Test
    public void whenFetchById_thenReturnItem() {
        Item item = Item.builder().name("Hamburger").build();

        Mockito.when(itemRepository.getById(1L)).thenReturn(item);

        Item fetchedItem = itemService.getById(1L);

        assertEquals(item.getId(), fetchedItem.getId());
    }

    @Test
    public void whenAddItem_thenReturnSavedItem() {
        Item item = Item.builder().name("Hamburger").build();

        Mockito.doReturn(item).when(itemRepository).save(item);

        Item returnedItem = itemService.create(item);

        assertEquals(item.getName(), returnedItem.getName());
    }

}