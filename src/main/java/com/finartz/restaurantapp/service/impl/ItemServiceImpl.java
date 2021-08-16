package com.finartz.restaurantapp.service.impl;

import com.finartz.restaurantapp.exception.EntityNotFoundException;
import com.finartz.restaurantapp.model.converter.dtoconverter.ItemDtoConverter;
import com.finartz.restaurantapp.model.converter.entityconverter.fromCreateRequest.ItemCreateRequestToEntityConverter;
import com.finartz.restaurantapp.model.dto.ItemDto;
import com.finartz.restaurantapp.model.dto.PageDto;
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
import java.util.Optional;

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

        PageDto page = new PageDto(Optional.of(items), 5);

        return items;
    }

    @Override
    public ItemDto getItem(Long id){
        return itemDtoConverter.convert(itemRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Not found Item with id: " + id)
        ));
    }

    @Override
    public ItemDto createItem(ItemCreateRequest itemCreateRequest){
        try{
            ItemEntity itemEntity = itemCreateRequestToEntityConverter.convert(itemCreateRequest);
            return itemDtoConverter.convert(itemRepository.save(itemEntity));
        }catch (IllegalArgumentException iae){
            throw new IllegalArgumentException("Invalid create request");
        }



    }

}
