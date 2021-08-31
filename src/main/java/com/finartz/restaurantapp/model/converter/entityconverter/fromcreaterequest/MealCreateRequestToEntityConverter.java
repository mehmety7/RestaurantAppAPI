package com.finartz.restaurantapp.model.converter.entityconverter.fromcreaterequest;

import com.finartz.restaurantapp.exception.EntityNotFoundException;
import com.finartz.restaurantapp.model.converter.GenericConverter;
import com.finartz.restaurantapp.model.entity.ItemEntity;
import com.finartz.restaurantapp.model.entity.MealEntity;
import com.finartz.restaurantapp.model.entity.MenuEntity;
import com.finartz.restaurantapp.model.request.create.MealCreateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class MealCreateRequestToEntityConverter implements GenericConverter<MealCreateRequest, MealEntity> {

    @Override
    public MealEntity convert(final MealCreateRequest mealCreateRequest){
        if (Objects.isNull(mealCreateRequest)){
            throw new EntityNotFoundException("Not found meal create request");
        }

        MealEntity mealEntity = new MealEntity();

        mealEntity.setName(mealCreateRequest.getName());
        mealEntity.setPrice(mealCreateRequest.getPrice());

        MenuEntity menuEntity = new MenuEntity();
        if(Objects.nonNull(mealCreateRequest.getMenuId())){
            menuEntity.setId(mealCreateRequest.getMenuId());
        }
        mealEntity.setMenuEntity(menuEntity);

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