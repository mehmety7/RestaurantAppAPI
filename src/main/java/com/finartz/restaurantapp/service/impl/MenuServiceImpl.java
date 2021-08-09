package com.finartz.restaurantapp.service.impl;

import com.finartz.restaurantapp.model.converter.dto.MenuDtoConverter;
import com.finartz.restaurantapp.model.converter.entity.fromCreateRequest.MenuCreateRequestToEntityConverter;
import com.finartz.restaurantapp.model.converter.entity.fromUpdateRequest.MenuUpdateRequestToEntityConverter;
import com.finartz.restaurantapp.model.dto.MenuDto;
import com.finartz.restaurantapp.model.entity.MenuEntity;
import com.finartz.restaurantapp.model.request.create.MenuCreateRequest;
import com.finartz.restaurantapp.model.request.update.MenuUpdateRequest;
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
    private final MenuUpdateRequestToEntityConverter menuUpdateRequestToEntityConverter;


    @Override
    public MenuDto getMenu(Long id){
        return menuDtoConverter.convert(menuRepository.getById(id));
    }

    @Override
    public MenuDto createMenu(MenuCreateRequest menuCreateRequest){
        MenuEntity menuEntity = menuCreateRequestToEntityConverter.convert(menuCreateRequest);
        return menuDtoConverter.convert(menuRepository.save(menuEntity));
    }

    @Override
    public MenuDto updateMenu(Long id, MenuUpdateRequest menuUpdateRequest) {
        MenuEntity menuExisted = menuRepository.getById(id);

        MenuEntity menuUpdated =
                menuUpdateRequestToEntityConverter.convert(menuUpdateRequest, menuExisted);

        return menuDtoConverter.convert(menuRepository.save(menuUpdated));
    }

}

