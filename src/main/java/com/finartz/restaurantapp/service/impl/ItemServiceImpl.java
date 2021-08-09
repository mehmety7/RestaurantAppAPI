package com.finartz.restaurantapp.service.impl;

import com.finartz.restaurantapp.model.converter.dto.ItemDtoConverter;
import com.finartz.restaurantapp.model.converter.entity.fromCreateRequest.ItemCreateRequestToEntityConverter;
import com.finartz.restaurantapp.model.dto.ItemDto;
import com.finartz.restaurantapp.model.entity.ItemEntity;
import com.finartz.restaurantapp.model.request.create.ItemCreateRequest;
import com.finartz.restaurantapp.repository.ItemRepository;
import com.finartz.restaurantapp.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;
    private final ItemDtoConverter itemDtoConverter;
    private final ItemCreateRequestToEntityConverter itemCreateRequestToEntityConverter;

    @Override
    public List<ItemDto> getItems(Integer pageNo, Integer pageSize, String sortBy) {

        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));

        Page<ItemEntity> itemEntityPage = itemRepository.findAll(paging);
        List<ItemDto> items = new ArrayList<>();
        itemEntityPage.forEach(itemEntity -> {
            items.add(itemDtoConverter.convert(itemEntity));
        });
        return items;
    }

    @Override
    public ItemDto getItem(Long id){
        return itemDtoConverter.convert(itemRepository.getById(id));
    }

    @Override
    public ItemDto createItem(ItemCreateRequest itemCreateRequest){
        ItemEntity itemEntity = itemCreateRequestToEntityConverter.convert(itemCreateRequest);
        return itemDtoConverter.convert(itemRepository.save(itemEntity));
    }

}
