package com.finartz.restaurantapp.model.converter.dtoconverter;

import com.finartz.restaurantapp.model.converter.GenericConverter;
import com.finartz.restaurantapp.model.dto.RestaurantDto;
import com.finartz.restaurantapp.model.entity.RestaurantEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RestaurantDtoConverter implements GenericConverter<RestaurantEntity, RestaurantDto> {

    @Override
    public RestaurantDto convert(final RestaurantEntity restaurantEntity){
        if(restaurantEntity == null){
            return null;
        }

        RestaurantDto restaurantDto = new RestaurantDto();

        restaurantDto.setId(restaurantEntity.getId());
        restaurantDto.setName(restaurantEntity.getName());
        restaurantDto.setStatus(restaurantEntity.getStatus());
        restaurantDto.setUserId(restaurantEntity.getUserEntity().getId());

        return restaurantDto;
    }

}
