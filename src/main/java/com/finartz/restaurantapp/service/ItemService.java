package com.finartz.restaurantapp.service;

import com.finartz.restaurantapp.model.dto.ItemDto;
import com.finartz.restaurantapp.model.dto.PageDto;
import com.finartz.restaurantapp.model.request.create.ItemCreateRequest;
import com.finartz.restaurantapp.model.request.get.ItemPageGetRequest;

public interface ItemService {

    PageDto<ItemDto> getItems(ItemPageGetRequest itemPageGetRequest);

    ItemDto getItem(Long id);

    ItemDto createItem(ItemCreateRequest itemCreateRequest);

}
