package com.finartz.restaurantapp.service;

import com.finartz.restaurantapp.model.dto.ItemDto;
import com.finartz.restaurantapp.model.dto.PageDto;
import com.finartz.restaurantapp.model.request.create.ItemCreateRequest;

public interface ItemService {

    PageDto<ItemDto> getItems(Integer pageNo, Integer pageSize, String sortBy);

    ItemDto getItem(Long id);

    ItemDto createItem(ItemCreateRequest itemCreateRequest);

}
