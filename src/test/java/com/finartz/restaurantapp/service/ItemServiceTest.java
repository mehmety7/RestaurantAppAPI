package com.finartz.restaurantapp.service;

import com.finartz.restaurantapp.model.converter.dtoconverter.ItemDtoConverter;
import com.finartz.restaurantapp.model.converter.entityconverter.fromCreateRequest.ItemCreateRequestToEntityConverter;
import com.finartz.restaurantapp.model.dto.ItemDto;
import com.finartz.restaurantapp.model.entity.ItemEntity;
import com.finartz.restaurantapp.model.request.create.ItemCreateRequest;
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

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
    public void whenFetchAll_thenReturnAllItem() {
        ItemEntity itemEntity = ItemEntity.builder().id(1l).name(NAME_HAMBURGER).build();
        ItemDto item = ItemDto.builder().id(1l).name(NAME_HAMBURGER).build();

        List<ItemEntity> itemEntities = Arrays.asList(itemEntity);
        List<ItemDto> items = Arrays.asList(item);
        Page<ItemEntity> itemEntityPage = new PageImpl<>(itemEntities);

        Mockito.when(itemDtoConverter.convert(itemEntity)).thenReturn(item);
        Mockito.when(itemRepository.findAll(PageRequest.of(0, 10, Sort.by("id")))).thenReturn(itemEntityPage);

        List<ItemDto> resultItems = itemService.getItems(0,10,"id");

        assertEquals(items.size(), resultItems.size());
    }

    @Test
    public void whenFetchById_thenReturnItem() {
        ItemEntity itemEntity = ItemEntity.builder().name(NAME_HAMBURGER).build();
        ItemDto item = ItemDto.builder().name(NAME_HAMBURGER).build();

        Mockito.when(itemDtoConverter.convert(itemEntity)).thenReturn(item);
        Mockito.when(itemRepository.getById(1L)).thenReturn(itemEntity);

        ItemDto resultItem = itemService.getItem(1L);

        assertEquals(item, resultItem);
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