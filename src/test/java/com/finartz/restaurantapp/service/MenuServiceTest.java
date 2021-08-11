package com.finartz.restaurantapp.service;

import com.finartz.restaurantapp.model.converter.dtoconverter.MenuDtoConverter;
import com.finartz.restaurantapp.model.converter.entityconverter.fromCreateRequest.MenuCreateRequestToEntityConverter;
import com.finartz.restaurantapp.model.dto.MenuDto;
import com.finartz.restaurantapp.model.entity.MenuEntity;
import com.finartz.restaurantapp.model.request.create.MenuCreateRequest;
import com.finartz.restaurantapp.repository.MenuRepository;
import com.finartz.restaurantapp.service.impl.MenuServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class MenuServiceTest {

    @InjectMocks
    private MenuServiceImpl menuService;

    @Mock
    private MenuRepository menuRepository;

    @Mock
    private MenuDtoConverter menuDtoConverter;

    @Mock
    private MenuCreateRequestToEntityConverter menuCreateRequestToEntityConverter;

    @Test
    public void whenFetchById_thenReturnMenu() {
        MenuEntity menuEntity = MenuEntity.builder().id(1L).build();
        MenuDto menu = MenuDto.builder().id(1L).build();

        Mockito.when(menuRepository.getById(1L)).thenReturn(menuEntity);
        Mockito.when(menuDtoConverter.convert(menuEntity)).thenReturn(menu);

        MenuDto resultMenu = menuService.getMenu(1L);

        assertEquals(menu.getId(), resultMenu.getId());
    }

    @Test
    public void whenAddMenu_thenReturnSavedMenu() {
        MenuEntity menuEntity = MenuEntity.builder().id(1L).build();
        MenuDto menu = MenuDto.builder().id(1L).build();
        MenuCreateRequest menuCreateRequest = MenuCreateRequest.builder().build();

        Mockito.when(menuCreateRequestToEntityConverter.convert(menuCreateRequest)).thenReturn(menuEntity);
        Mockito.when(menuRepository.save(menuEntity)).thenReturn(menuEntity);
        Mockito.when(menuDtoConverter.convert(menuEntity)).thenReturn(menu);

        MenuDto resultMenu = menuService.createMenu(menuCreateRequest);

        assertEquals(menu.getId(), resultMenu.getId());
    }

}