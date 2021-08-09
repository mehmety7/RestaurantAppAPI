package com.finartz.restaurantapp.service.impl;

import com.finartz.restaurantapp.model.converter.dto.RestaurantDtoConverter;
import com.finartz.restaurantapp.model.converter.entity.fromCreateRequest.RestaurantCreateRequestToEntityConverter;
import com.finartz.restaurantapp.model.converter.entity.fromUpdateRequest.RestaurantUpdateRequestToEntityConverter;
import com.finartz.restaurantapp.model.dto.RestaurantDto;
import com.finartz.restaurantapp.model.entity.RestaurantEntity;
import com.finartz.restaurantapp.model.enumerated.Status;
import com.finartz.restaurantapp.model.request.create.RestaurantCreateRequest;
import com.finartz.restaurantapp.model.request.update.RestaurantUpdateRequest;
import com.finartz.restaurantapp.repository.RestaurantRepository;
import com.finartz.restaurantapp.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RestaurantServiceImpl implements RestaurantService {

    private final RestaurantRepository restaurantRepository;
    private final RestaurantDtoConverter restaurantDtoConverter;
    private final RestaurantCreateRequestToEntityConverter restaurantCreateRequestToEntityConverter;
    private final RestaurantUpdateRequestToEntityConverter restaurantUpdateRequestToEntityConverter;

    @Override
    public List<RestaurantDto> getRestaurants(Status status){
        List<RestaurantEntity> restaurantEntities = restaurantRepository.findByStatus(status);
        List<RestaurantDto> restaurants = new ArrayList<>();
        restaurantEntities.forEach(restaurantEntity -> {
            restaurants.add(restaurantDtoConverter.convert(restaurantEntity));
        });
        return restaurants;
    }

    @Override
    public RestaurantDto getRestaurant(Long id){
        return restaurantDtoConverter.convert(restaurantRepository.getById(id));
    }

    @Override
    public RestaurantDto createRestaurant(RestaurantCreateRequest restaurantCreateRequest){
        RestaurantEntity restaurantEntity = restaurantCreateRequestToEntityConverter.convert(restaurantCreateRequest);
        return restaurantDtoConverter.convert(restaurantRepository.save(restaurantEntity));
    }

    @Override
    public RestaurantDto updateRestaurant(Long id, RestaurantUpdateRequest restaurantUpdateRequest){
        RestaurantEntity restaurantExisted = restaurantRepository.getById(id);
        RestaurantEntity restaurantUpdated =
                restaurantUpdateRequestToEntityConverter.convert(restaurantUpdateRequest, restaurantExisted);

        return restaurantDtoConverter.convert(restaurantRepository.save(restaurantUpdated));

    }

}
