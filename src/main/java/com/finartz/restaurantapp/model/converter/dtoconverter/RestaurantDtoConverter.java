package com.finartz.restaurantapp.model.converter.dtoconverter;

import com.finartz.restaurantapp.exception.EntityNotFoundException;
import com.finartz.restaurantapp.model.converter.GenericConverter;
import com.finartz.restaurantapp.model.dto.RestaurantDto;
import com.finartz.restaurantapp.model.entity.RestaurantEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@RequiredArgsConstructor
public class RestaurantDtoConverter implements GenericConverter<RestaurantEntity, RestaurantDto> {

    @Override
    public RestaurantDto convert(final RestaurantEntity restaurantEntity){
        if (Objects.isNull(restaurantEntity)){
            throw new EntityNotFoundException("Not found restaurant entity");
        }

        RestaurantDto restaurantDto = new RestaurantDto();

        restaurantDto.setId(restaurantEntity.getId());
        restaurantDto.setName(restaurantEntity.getName());
        restaurantDto.setRestaurantStatus(restaurantEntity.getRestaurantStatus());

        if(Objects.nonNull(restaurantEntity.getUserEntity())){
            restaurantDto.setUserId(restaurantEntity.getUserEntity().getId());
        }

        return restaurantDto;
    }

}
