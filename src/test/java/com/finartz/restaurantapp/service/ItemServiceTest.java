package com.finartz.restaurantapp.service;

import com.finartz.restaurantapp.exception.EntityNotFoundException;
import com.finartz.restaurantapp.model.converter.dtoconverter.ItemDtoConverter;
import com.finartz.restaurantapp.model.converter.entityconverter.fromcreaterequest.ItemCreateRequestToEntityConverter;
import com.finartz.restaurantapp.model.dto.ItemDto;
import com.finartz.restaurantapp.model.dto.PageDto;
import com.finartz.restaurantapp.model.entity.ItemEntity;
import com.finartz.restaurantapp.model.request.create.ItemCreateRequest;
import com.finartz.restaurantapp.model.request.get.ItemPageGetRequest;
import com.finartz.restaurantapp.repository.ItemRepository;
import com.finartz.restaurantapp.service.impl.ItemServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;

@RunWith(MockitoJUnitRunner.class)
public class ItemServiceTest {

    private static final String NAME_HAMBURGER = "Hamburger";

    @InjectMocks
    private ItemServiceImpl itemService;

    @Mock
    private ItemRepository itemRepository;

    @Mock
    private ItemDtoConverter itemDtoConverter;

    @Mock
    private ItemCreateRequestToEntityConverter itemCreateRequestToEntityConverter;


    @Test
    public void whenFetchItemPage_thenReturnItemPage() {
        ItemEntity itemEntity = ItemEntity.builder().id(1L).name(NAME_HAMBURGER).build();
        ItemDto item = ItemDto.builder().id(1L).name(NAME_HAMBURGER).build();
        ItemPageGetRequest itemPageGetRequest = ItemPageGetRequest.builder().pageNo(0).pageSize(1).sortBy("id").build();

        List<ItemEntity> itemEntities = Collections.singletonList(itemEntity);
        List<ItemDto> items = Collections.singletonList(item);

        Page<ItemEntity> itemEntityPage = new PageImpl<>(itemEntities);

        Mockito.when(itemDtoConverter.convert(itemEntity)).thenReturn(item);
        Mockito.when(itemRepository.findAll(PageRequest.of(0, 1, Sort.by("id")))).thenReturn(itemEntityPage);
        Mockito.when(itemRepository.countItemEntitiesBy()).thenReturn(items.size());

        PageDto<ItemDto> resultPage = itemService.getItems(itemPageGetRequest);

        assertEquals(items.size(), resultPage.getResponse().size());
        assertEquals(items.size(), resultPage.getTotalCount());
        assertEquals(items.size()+1, resultPage.getPageCount());
    }

    @Test
    public void whenFetchByValidId_thenReturnItem() {
        ItemEntity itemEntity = ItemEntity.builder().name(NAME_HAMBURGER).build();
        ItemDto item = ItemDto.builder().name(NAME_HAMBURGER).build();

        Mockito.when(itemDtoConverter.convert(itemEntity)).thenReturn(item);
        Mockito.when(itemRepository.findById(1L)).thenReturn(Optional.ofNullable(itemEntity));

        ItemDto resultItem = itemService.getItem(1L);

        assertEquals(item, resultItem);
    }

    @Test(expected = EntityNotFoundException.class)
    public void whenFetchByInvalidId_thenThrowEntityNotFoundException() {

        Mockito.when(itemRepository.findById(anyLong())).thenReturn(Optional.empty());
        itemService.getItem(anyLong());

    }

    @Test
    public void whenAddItem_thenReturnSavedItem() {
        ItemEntity itemEntity = ItemEntity.builder().name(NAME_HAMBURGER).build();
        ItemDto item = ItemDto.builder().name(NAME_HAMBURGER).build();
        ItemCreateRequest itemCreateRequest = ItemCreateRequest.builder().name(NAME_HAMBURGER).build();

        Mockito.when(itemCreateRequestToEntityConverter.convert(itemCreateRequest)).thenReturn(itemEntity);
        Mockito.when(itemRepository.save(itemEntity)).thenReturn(itemEntity);
        Mockito.when(itemDtoConverter.convert(itemEntity)).thenReturn(item);

        ItemDto resultItem = itemService.createItem(itemCreateRequest);

        assertEquals(item.getName(), resultItem.getName());
    }

}