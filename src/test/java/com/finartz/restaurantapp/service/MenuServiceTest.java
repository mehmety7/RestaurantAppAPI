package com.finartz.restaurantapp.service;

import com.finartz.restaurantapp.model.Menu;
import com.finartz.restaurantapp.repository.MenuRepository;
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
public class MenuServiceTest {

    @InjectMocks
    private MenuService menuService;

    @Mock
    private MenuRepository menuRepository;


    @Test
    public void whenFetchAll_thenReturnAllMenu() {
        Menu menu1 = Menu.builder().id(1l).build();
        Menu menu2 = Menu.builder().id(2l).build();
        List<Menu> menuList = Arrays.asList(menu1, menu2);

        Mockito.when(menuRepository.findAll()).thenReturn(menuList);

        List<Menu> fetchedList = menuService.getAll();

        assertEquals(menuList.size(), fetchedList.size());
    }

    @Test
    public void whenFetchById_thenReturnMenu() {
        Menu menu = Menu.builder().id(1L).build();

        Mockito.when(menuRepository.getById(1L)).thenReturn(menu);

        Menu fetchedMenu = menuService.getById(1L);

        assertEquals(menu.getId(), fetchedMenu.getId());
    }

    @Test
    public void whenAddMenu_thenReturnSavedMenu() {
        Menu menu = Menu.builder().id(1L).build();

        Mockito.doReturn(menu).when(menuRepository).save(menu);

        Menu returnedMenu = menuService.create(menu);

        assertEquals(menu.getId(), returnedMenu.getId());
    }

}