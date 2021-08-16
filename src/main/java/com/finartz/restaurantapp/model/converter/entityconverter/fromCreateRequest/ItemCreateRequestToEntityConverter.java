package com.finartz.restaurantapp.model.converter.entityconverter.fromCreateRequest;

import com.finartz.restaurantapp.model.converter.GenericConverter;
import com.finartz.restaurantapp.model.entity.ItemEntity;
import com.finartz.restaurantapp.model.request.create.ItemCreateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ItemCreateRequestToEntityConverter implements GenericConverter<ItemCreateRequest, ItemEntity> {

//    private final GenericConverter<MealDto, MealEntity> mealEntityConverter;

    @Override
    public ItemEntity convert(final ItemCreateRequest itemCreateRequest){
        if(itemCreateRequest == null){
            return null;
        }

        ItemEntity itemEntity = new ItemEntity();

        itemEntity.setName(itemCreateRequest.getName());
        itemEntity.setUnitType(itemCreateRequest.getUnitType());

//        List<MealEntity> mealEntities = new ArrayList<>();
//        itemCreateRequest.getMeals().forEach(meal -> {
//            mealEntities.add(convert(meal));
//        });
//        itemEntity.setMealEntities(mealEntities);

        return itemEntity;
    }

//    private MealEntity convert(final MealDto meal){
//        return mealEntityConverter.convert(meal);
//    }

}