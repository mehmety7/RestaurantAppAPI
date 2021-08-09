package com.finartz.restaurantapp.service;

import com.finartz.restaurantapp.model.dto.ItemDto;
import com.finartz.restaurantapp.model.request.create.ItemCreateRequest;

import java.util.List;

public interface ItemService {

    List<ItemDto> getItems(Integer pageNo, Integer pageSize, String sortBy);

    ItemDto getItem(Long id);

    ItemDto createItem(ItemCreateRequest itemCreateRequest);

}
