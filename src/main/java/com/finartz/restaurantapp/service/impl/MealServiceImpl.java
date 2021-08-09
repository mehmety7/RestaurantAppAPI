package com.finartz.restaurantapp.service.impl;

import com.finartz.restaurantapp.model.converter.dto.MealDtoConverter;
import com.finartz.restaurantapp.model.converter.entity.fromCreateRequest.MealCreateRequestToEntityConverter;
import com.finartz.restaurantapp.model.dto.MealDto;
import com.finartz.restaurantapp.model.entity.MealEntity;
import com.finartz.restaurantapp.model.request.create.MealCreateRequest;
import com.finartz.restaurantapp.repository.MealRepository;
import com.finartz.restaurantapp.service.MealService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MealServiceImpl implements MealService {

    private final MealRepository mealRepository;
    private final MealDtoConverter mealDtoConverter;
    private final MealCreateRequestToEntityConverter mealCreateRequestToEntityConverter;

    @Override
    public MealDto getMeal(Long id){
        return mealDtoConverter.convert(mealRepository.getById(id));
    }

    @Override
    public MealDto createMeal(MealCreateRequest mealCreateRequest){
        MealEntity mealEntity = mealCreateRequestToEntityConverter.convert(mealCreateRequest);
        return mealDtoConverter.convert(mealRepository.save(mealEntity));
    }

}
