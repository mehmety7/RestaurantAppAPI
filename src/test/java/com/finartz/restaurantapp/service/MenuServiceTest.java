package com.finartz.restaurantapp.service;

import com.finartz.restaurantapp.exception.EntityNotFoundException;
import com.finartz.restaurantapp.exception.InvalidCreatingException;
import com.finartz.restaurantapp.model.converter.dtoconverter.MenuDtoConverter;
import com.finartz.restaurantapp.model.converter.entityconverter.fromcreaterequest.MenuCreateRequestToEntityConverter;
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

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;

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
    public void whenFetchByValidId_thenReturnMenu() {
        MenuEntity menuEntity = MenuEntity.builder().id(1L).build();
        MenuDto menu = MenuDto.builder().id(1L).build();

        Mockito.when(menuRepository.findById(1L)).thenReturn(Optional.ofNullable(menuEntity));
        Mockito.when(menuDtoConverter.convert(menuEntity)).thenReturn(menu);

        MenuDto resultMenu = menuService.getMenu(1L);

        assertEquals(menu.getId(), resultMenu.getId());
    }

    @Test(expected = EntityNotFoundException.class)
    public void whenFetchByInvalidId_thenThrowEntityNotFoundException() {

        Mockito.when(menuRepository.findById(anyLong())).thenReturn(Optional.empty());
        menuService.getMenu(anyLong());

    }

    @Test
    public void whenFetchByValidBranchId_thenReturnMenu() {
        MenuEntity menuEntity = MenuEntity.builder().id(1L).build();
        MenuDto menu = MenuDto.builder().id(1L).build();

        Mockito.when(menuRepository.getMenuEntityByBranchEntityId(anyLong())).thenReturn(menuEntity);
        Mockito.when(menuDtoConverter.convert(menuEntity)).thenReturn(menu);

        MenuDto resultMenu = menuService.getBranchMenu(anyLong());

        assertEquals(menu.getId(), resultMenu.getId());
    }

    @Test(expected = EntityNotFoundException.class)

    public void whenFetchByInvalidBranchId_thenThrowEntityNotFoundException() {

        Mockito.when(menuRepository.getMenuEntityByBranchEntityId(anyLong())).thenReturn(null);
        menuService.getBranchMenu(anyLong());

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

    @Test(expected = InvalidCreatingException.class)
    public void givenExistingMenu_whenAddNewMenu_ThenThrowInvalidCreatingException(){
        MenuEntity menuEntity = MenuEntity.builder().build();
        MenuCreateRequest menuCreateRequest = MenuCreateRequest.builder().branchId(anyLong()).build();

        Mockito.when(menuRepository.getMenuEntityByBranchEntityId(menuCreateRequest.getBranchId())).thenReturn(menuEntity);
        menuService.createMenu(menuCreateRequest);

    }

}