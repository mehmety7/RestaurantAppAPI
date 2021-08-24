package com.finartz.restaurantapp.service.impl;

import com.finartz.restaurantapp.exception.EntityNotFoundException;
import com.finartz.restaurantapp.exception.InvalidCreatingException;
import com.finartz.restaurantapp.model.converter.dtoconverter.MenuDtoConverter;
import com.finartz.restaurantapp.model.converter.entityconverter.fromCreateRequest.MenuCreateRequestToEntityConverter;
import com.finartz.restaurantapp.model.dto.MenuDto;
import com.finartz.restaurantapp.model.entity.MenuEntity;
import com.finartz.restaurantapp.model.request.create.MenuCreateRequest;
import com.finartz.restaurantapp.repository.MenuRepository;
import com.finartz.restaurantapp.service.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
@RequiredArgsConstructor
@Transactional
public class MenuServiceImpl implements MenuService {

    private final MenuRepository menuRepository;
    private final MenuDtoConverter menuDtoConverter;
    private final MenuCreateRequestToEntityConverter menuCreateRequestToEntityConverter;

    @Override
    public MenuDto getMenu(Long id){
        return menuDtoConverter.convert(menuRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Not found Menu with id:" + id)
        ));
    }

    @Override
    public MenuDto getMenuByBranchId(Long branchId){
        MenuEntity menuEntity = menuRepository.getMenuEntityByBranchEntity_Id(branchId);
        if(Objects.nonNull(menuEntity.getId())){
            return menuDtoConverter.convert(menuEntity);
        }else{
            throw new EntityNotFoundException("Not found Menu with branch id:" + branchId);
        }
    }

    @Override
    public MenuDto getBranchMenu(Long branch_id) {
        return menuDtoConverter.convert(menuRepository.getMenuEntityByBranchEntity_Id(branch_id));
    }

    //unnecessary method (ask it)
    @Override
    public MenuDto createMenu(MenuCreateRequest menuCreateRequest){
        // Check the person is seller of the restaurant who attempt to create menu
        MenuEntity existingMenu = menuRepository.getMenuEntityByBranchEntity_Id(menuCreateRequest.getBranchId());

        if (Objects.isNull(existingMenu)) {
            MenuEntity menuEntity = menuCreateRequestToEntityConverter.convert(menuCreateRequest);
            return menuDtoConverter.convert(menuRepository.save(menuEntity));
        } else {
            throw new InvalidCreatingException("Branch ' " + menuCreateRequest.getBranchId() + " ' has already a menu");
        }
    }

}

