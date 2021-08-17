package com.finartz.restaurantapp.model.converter.entityconverter.fromCreateRequest;

import com.finartz.restaurantapp.model.converter.GenericConverter;
import com.finartz.restaurantapp.model.entity.BranchEntity;
import com.finartz.restaurantapp.model.entity.MenuEntity;
import com.finartz.restaurantapp.model.request.create.MenuCreateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MenuCreateRequestToEntityConverter implements GenericConverter<MenuCreateRequest, MenuEntity> {

    @Override
    public MenuEntity convert(final MenuCreateRequest menuCreateRequest){
        if(menuCreateRequest == null){
            return null;
        }

        MenuEntity menuEntity = new MenuEntity();

        BranchEntity branchEntity = new BranchEntity();
        branchEntity.setId(menuCreateRequest.getBranchId());
        menuEntity.setBranchEntity(branchEntity);

        return menuEntity;
    }

}