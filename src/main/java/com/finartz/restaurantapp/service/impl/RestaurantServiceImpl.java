package com.finartz.restaurantapp.service.impl;

import com.finartz.restaurantapp.exception.EntityNotFoundException;
import com.finartz.restaurantapp.model.converter.dtoconverter.RestaurantDtoConverter;
import com.finartz.restaurantapp.model.converter.entityconverter.fromcreaterequest.RestaurantCreateRequestToEntityConverter;
import com.finartz.restaurantapp.model.converter.entityconverter.fromupdaterequest.RestaurantUpdateRequestToEntityConverter;
import com.finartz.restaurantapp.model.dto.RestaurantDto;
import com.finartz.restaurantapp.model.entity.RestaurantEntity;
import com.finartz.restaurantapp.model.enumerated.RestaurantStatus;
import com.finartz.restaurantapp.model.request.create.RestaurantCreateRequest;
import com.finartz.restaurantapp.model.request.update.RestaurantUpdateRequest;
import com.finartz.restaurantapp.repository.RestaurantRepository;
import com.finartz.restaurantapp.service.RestaurantService;
import com.finartz.restaurantapp.service.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class RestaurantServiceImpl implements RestaurantService {

    private final RestaurantRepository restaurantRepository;
    private final RestaurantDtoConverter restaurantDtoConverter;
    private final RestaurantCreateRequestToEntityConverter restaurantCreateRequestToEntityConverter;
    private final RestaurantUpdateRequestToEntityConverter restaurantUpdateRequestToEntityConverter;

    private final TokenService tokenService;


    @Override
    @Cacheable(value = "restaurantCache")
    public List<RestaurantDto> getRestaurants(RestaurantStatus restaurantStatus){
        try{
            Thread.sleep(1000);
        }catch(Exception e){
            e.printStackTrace();
        }
        List<RestaurantEntity> restaurantEntities = restaurantRepository.findByRestaurantStatus(restaurantStatus);
        List<RestaurantDto> restaurants = new ArrayList<>();
        restaurantEntities.forEach(restaurantEntity -> {
            restaurants.add(restaurantDtoConverter.convert(restaurantEntity));
        });
        return restaurants;
    }

    @Override
    public RestaurantDto getRestaurant(Long id){
        return restaurantDtoConverter.convert(restaurantRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Not found Restaurant with id:" + id)
        ));
    }

    @Override
    public RestaurantDto createRestaurant(RestaurantCreateRequest restaurantCreateRequest){
        if (tokenService.isRequestOwnerAuthoritative(restaurantCreateRequest.getUserId())){}

        RestaurantEntity restaurantEntity = restaurantCreateRequestToEntityConverter.convert(restaurantCreateRequest);
        return restaurantDtoConverter.convert(restaurantRepository.save(restaurantEntity));
    }

    @Override
    @Transactional
    @CacheEvict(value = "restaurantCache", allEntries = true)
    public RestaurantDto updateRestaurantStatus(RestaurantUpdateRequest restaurantUpdateRequest){
        RestaurantEntity restaurantExisted = restaurantRepository.getById(restaurantUpdateRequest.getId());
        if(Objects.nonNull(restaurantExisted)){
            RestaurantEntity restaurantUpdated = restaurantUpdateRequestToEntityConverter.convert(restaurantUpdateRequest, restaurantExisted);
            return restaurantDtoConverter.convert(restaurantRepository.save(restaurantUpdated));
        }else {
            throw new EntityNotFoundException("Not found Restaurant with id: " + restaurantUpdateRequest.getId());
        }

    }

    @Override
    public Boolean isRestaurantApproved(Long restaurant_id){
        RestaurantEntity restaurantEntity = restaurantRepository.getById(restaurant_id);
        if(restaurantEntity.getRestaurantStatus().equals(RestaurantStatus.APPROVED))
            return true;
        else
            return false;
    }

}
