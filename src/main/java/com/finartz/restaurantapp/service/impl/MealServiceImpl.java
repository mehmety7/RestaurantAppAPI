package com.finartz.restaurantapp.service.impl;

import com.finartz.restaurantapp.exception.EntityNotFoundException;
import com.finartz.restaurantapp.exception.MissingArgumentsException;
import com.finartz.restaurantapp.model.converter.dtoconverter.MealDtoConverter;
import com.finartz.restaurantapp.model.converter.entityconverter.fromcreaterequest.MealCreateRequestToEntityConverter;
import com.finartz.restaurantapp.model.dto.MealDto;
import com.finartz.restaurantapp.model.dto.MenuDto;
import com.finartz.restaurantapp.model.entity.MealEntity;
import com.finartz.restaurantapp.model.request.create.MealCreateRequest;
import com.finartz.restaurantapp.repository.MealRepository;
import com.finartz.restaurantapp.service.MealService;
import com.finartz.restaurantapp.service.MenuService;
import com.finartz.restaurantapp.service.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class MealServiceImpl implements MealService {

    private final MealRepository mealRepository;
    private final MealDtoConverter mealDtoConverter;
    private final MealCreateRequestToEntityConverter mealCreateRequestToEntityConverter;

    private final MenuService menuService;
    private final TokenService tokenService;

    @Override
    @Transactional
    public MealDto getMeal(Long id){
        return mealDtoConverter.convert(mealRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Not found Meal with id:" + id)
        ));
    }

    @Override
    @Transactional
    public MealDto createMeal(MealCreateRequest mealCreateRequest){
        if(Objects.isNull(mealCreateRequest.getMenuId()) && Objects.isNull(mealCreateRequest.getBranchId())){
            throw new MissingArgumentsException("Either branch id or menu id may not be null for meal creating operation");
        }else if(Objects.nonNull(mealCreateRequest.getMenuId())){
            MenuDto menu = menuService.getMenu(mealCreateRequest.getMenuId());
            mealCreateRequest.setBranchId(menu.getBranchId());
        }else if(Objects.nonNull(mealCreateRequest.getBranchId())){
            MenuDto menu = menuService.getBranchMenu(mealCreateRequest.getBranchId());
            mealCreateRequest.setMenuId(menu.getId());
        }

        Long entityOwnerId = mealRepository.getEntityOwnerUserIdByMenuId(mealCreateRequest.getMenuId());
        if(Objects.nonNull(entityOwnerId) && tokenService.isRequestOwnerAuthoritative(entityOwnerId)){}

        MealEntity mealEntity = mealCreateRequestToEntityConverter.convert(mealCreateRequest);
        return mealDtoConverter.convert(mealRepository.save(mealEntity));
    }

}
