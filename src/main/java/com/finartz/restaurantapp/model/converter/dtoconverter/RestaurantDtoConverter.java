package com.finartz.restaurantapp.model.converter.dtoconverter;

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
        if(Objects.isNull(restaurantEntity)){
            return null;
        }

        RestaurantDto restaurantDto = new RestaurantDto();

        if(Objects.nonNull(restaurantEntity.getId())){
            restaurantDto.setId(restaurantEntity.getId());
        }
        if(Objects.nonNull(restaurantEntity.getName())){
            restaurantDto.setName(restaurantEntity.getName());
        }
        if(Objects.nonNull(restaurantEntity.getStatus())){
            restaurantDto.setStatus(restaurantEntity.getStatus());
        }
        if(Objects.nonNull(restaurantEntity.getUserEntity())){
            restaurantDto.setUserId(restaurantEntity.getUserEntity().getId());
        }

        return restaurantDto;
    }

}
