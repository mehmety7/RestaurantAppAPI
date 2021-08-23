package com.finartz.restaurantapp.model.converter.entityconverter.fromCreateRequest;

import com.finartz.restaurantapp.exception.MissingArgumentsException;
import com.finartz.restaurantapp.model.converter.GenericConverter;
import com.finartz.restaurantapp.model.entity.ItemEntity;
import com.finartz.restaurantapp.model.entity.MealEntity;
import com.finartz.restaurantapp.model.entity.MenuEntity;
import com.finartz.restaurantapp.model.request.create.MealCreateRequest;
import com.finartz.restaurantapp.repository.MenuRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class MealCreateRequestToEntityConverter implements GenericConverter<MealCreateRequest, MealEntity> {

    @Autowired
    private MenuRepository menuRepository;

    @Override
    public MealEntity convert(final MealCreateRequest mealCreateRequest){
        if(Objects.isNull(mealCreateRequest)){
            return null;
        }

        MealEntity mealEntity = new MealEntity();

        mealEntity.setName(mealCreateRequest.getName());
        mealEntity.setPrice(mealCreateRequest.getPrice());

        if(Objects.nonNull(mealCreateRequest.getMenuId())){
            MenuEntity menuEntity = new MenuEntity();
            menuEntity.setId(mealCreateRequest.getMenuId());
            mealEntity.setMenuEntity(menuEntity);
        }else if(Objects.nonNull(mealCreateRequest.getBranchId())){
            MenuEntity menuEntity = menuRepository.getMenuEntityByBranchEntity_Id(mealCreateRequest.getBranchId());
            mealEntity.setMenuEntity(menuEntity);
        }else {
            throw new MissingArgumentsException("Either branch id or menu id may not be null for meal creating operation");
        }

        List<ItemEntity> itemEntities = new ArrayList<>();
        if(Objects.nonNull(mealCreateRequest.getItemIds())) {
            itemEntities = setItemEntityId(mealCreateRequest.getItemIds());
        }
        mealEntity.setItemEntities(itemEntities);

        return mealEntity;
    }

    private List<ItemEntity> setItemEntityId(List<Long> ids) {
        List<ItemEntity> itemEntities = new ArrayList<>();
        for (int i = 0; i < ids.size(); i++) {
            ItemEntity itemEntity = new ItemEntity();
            itemEntity.setId(ids.get(i));
            itemEntities.add(itemEntity);
        }
        return itemEntities;
    }

}