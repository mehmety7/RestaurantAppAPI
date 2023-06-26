package com.finartz.restaurantapp.service.impl;

import com.finartz.restaurantapp.exception.EntityNotFoundException;
import com.finartz.restaurantapp.model.converter.dtoconverter.ItemDtoConverter;
import com.finartz.restaurantapp.model.converter.entityconverter.fromcreaterequest.ItemCreateRequestToEntityConverter;
import com.finartz.restaurantapp.model.dto.ItemDto;
import com.finartz.restaurantapp.model.dto.PageDto;
import com.finartz.restaurantapp.model.entity.ItemEntity;
import com.finartz.restaurantapp.model.request.create.ItemCreateRequest;
import com.finartz.restaurantapp.model.request.get.ItemPageGetRequest;
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
    public PageDto<ItemDto> getItems(ItemPageGetRequest itemPageGetRequest) {

        Pageable paging = PageRequest.of(itemPageGetRequest.getPageNo(), itemPageGetRequest.getPageSize(), Sort.by(itemPageGetRequest.getSortBy()));
        Page<ItemEntity> itemEntityPage = itemRepository.findAll(paging);
        List<ItemDto> itemPage = new ArrayList<>();
        itemEntityPage.forEach(itemEntity -> itemPage.add(itemDtoConverter.convert(itemEntity)));

        Integer totalCount = itemRepository.countItemEntitiesBy();
        Integer pageCount = (totalCount / itemPageGetRequest.getPageSize()) + 1;

        return new PageDto<>(itemPage, totalCount, pageCount);
    }

    @Override
    public ItemDto getItem(Long id){
        return itemDtoConverter.convert(itemRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Not found Item with id: " + id)
        ));
    }

    @Override
    public ItemDto createItem(ItemCreateRequest itemCreateRequest){
        ItemEntity itemEntity = itemCreateRequestToEntityConverter.convert(itemCreateRequest);
        return itemDtoConverter.convert(itemRepository.save(itemEntity));

    }

}
