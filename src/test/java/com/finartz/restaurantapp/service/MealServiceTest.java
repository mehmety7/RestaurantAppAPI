package com.finartz.restaurantapp.service;

import com.finartz.restaurantapp.exception.EntityNotFoundException;
import com.finartz.restaurantapp.exception.MissingArgumentsException;
import com.finartz.restaurantapp.model.converter.dtoconverter.MealDtoConverter;
import com.finartz.restaurantapp.model.converter.entityconverter.fromCreateRequest.MealCreateRequestToEntityConverter;
import com.finartz.restaurantapp.model.dto.MealDto;
import com.finartz.restaurantapp.model.dto.MenuDto;
import com.finartz.restaurantapp.model.entity.MealEntity;
import com.finartz.restaurantapp.model.request.create.MealCreateRequest;
import com.finartz.restaurantapp.repository.MealRepository;
import com.finartz.restaurantapp.service.impl.MealServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;

@RunWith(MockitoJUnitRunner.class)
public class MealServiceTest {

    private static final String NAME_FIRSAT = "Fırsat Menü";

    @InjectMocks
    private MealServiceImpl mealService;

    @Mock
    private MealRepository mealRepository;

    @Mock
    private MealDtoConverter mealDtoConverter;

    @Mock
    private MealCreateRequestToEntityConverter mealCreateRequestToEntityConverter;

    @Mock
    private MenuService menuService;

    @Mock
    private BranchService branchService;

    @Mock
    private RestaurantService restaurantService;

    @Test
    public void whenFetchByValidId_thenReturnMeal() {
        MealEntity mealEntity = MealEntity.builder().name(NAME_FIRSAT).build();
        MealDto meal = MealDto.builder().name(NAME_FIRSAT).build();

        Mockito.when(mealRepository.findById(1L)).thenReturn(Optional.of(mealEntity));
        Mockito.when(mealDtoConverter.convert(mealEntity)).thenReturn(meal);

        MealDto resultMeal = mealService.getMeal(1L);

        assertEquals(meal, resultMeal);
    }

    @Test(expected = EntityNotFoundException.class)
    public void whenFetchByInvalidId_thenThrowEntityNotFoundException() {

        Mockito.when(mealRepository.findById(anyLong())).thenReturn(Optional.empty());
        mealService.getMeal(anyLong());

    }

    @Test
    public void givenValidMenuId_whenAddMeal_thenReturnSavedMeal() {
        MealEntity mealEntity = MealEntity.builder().name(NAME_FIRSAT).build();
        MealDto meal = MealDto.builder().name(NAME_FIRSAT).build();
        MealCreateRequest mealCreateRequest = MealCreateRequest.builder().menuId(1l).name(NAME_FIRSAT).build();

        MenuDto menu = MenuDto.builder().id(1l).branchId(1l).build();

        Mockito.when(menuService.getMenu(1l)).thenReturn(menu);

        Mockito.when(mealCreateRequestToEntityConverter.convert(mealCreateRequest)).thenReturn(mealEntity);
        Mockito.when(mealRepository.save(mealEntity)).thenReturn(mealEntity);
        Mockito.when(mealDtoConverter.convert(mealEntity)).thenReturn(meal);

        MealDto resultMeal = mealService.createMeal(mealCreateRequest);

        assertEquals(meal.getName(), resultMeal.getName());
    }

    @Test
    public void givenValidBranchId_whenAddMeal_thenReturnSavedMeal() {
        MealEntity mealEntity = MealEntity.builder().name(NAME_FIRSAT).build();
        MealDto meal = MealDto.builder().name(NAME_FIRSAT).build();
        MealCreateRequest mealCreateRequest = MealCreateRequest.builder().branchId(1l).name(NAME_FIRSAT).build();

        MenuDto menu = MenuDto.builder().id(1l).branchId(1l).build();

        Mockito.when(menuService.getBranchMenu(1l)).thenReturn(menu);

        Mockito.when(mealCreateRequestToEntityConverter.convert(mealCreateRequest)).thenReturn(mealEntity);
        Mockito.when(mealRepository.save(mealEntity)).thenReturn(mealEntity);
        Mockito.when(mealDtoConverter.convert(mealEntity)).thenReturn(meal);

        MealDto resultMeal = mealService.createMeal(mealCreateRequest);

        assertEquals(meal.getName(), resultMeal.getName());
    }

    @Test(expected = MissingArgumentsException.class)
    public void givenBothNotExistBranchIdAndMenuId_whenAddMeal_thenThrowMissingArgumentException() {

        MealCreateRequest mealCreateRequest = MealCreateRequest.builder().menuId(null).branchId(null).build();
        mealService.createMeal(mealCreateRequest);

    }



}