package com.finartz.restaurantapp.service.impl;

import com.finartz.restaurantapp.model.converter.dtoconverter.MenuDtoConverter;
import com.finartz.restaurantapp.model.converter.entityconverter.fromCreateRequest.MenuCreateRequestToEntityConverter;
import com.finartz.restaurantapp.model.dto.MenuDto;
import com.finartz.restaurantapp.model.entity.MenuEntity;
import com.finartz.restaurantapp.model.request.create.MenuCreateRequest;
import com.finartz.restaurantapp.repository.MenuRepository;
import com.finartz.restaurantapp.service.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MenuServiceImpl implements MenuService {

    private final MenuRepository menuRepository;
    private final MenuDtoConverter menuDtoConverter;
    private final MenuCreateRequestToEntityConverter menuCreateRequestToEntityConverter;


    @Override
    public MenuDto getMenu(Long id){
        return menuDtoConverter.convert(menuRepository.getById(id));
    }

    @Override
    public MenuDto createMenu(MenuCreateRequest menuCreateRequest){
        MenuEntity menuEntity = menuCreateRequestToEntityConverter.convert(menuCreateRequest);
        return menuDtoConverter.convert(menuRepository.save(menuEntity));
    }


}

