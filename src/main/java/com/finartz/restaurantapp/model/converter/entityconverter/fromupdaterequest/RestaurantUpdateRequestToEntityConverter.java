package com.finartz.restaurantapp.model.converter.entityconverter.fromupdaterequest;

import com.finartz.restaurantapp.exception.EntityNotFoundException;
import com.finartz.restaurantapp.model.entity.RestaurantEntity;
import com.finartz.restaurantapp.model.request.update.RestaurantUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@RequiredArgsConstructor
public class RestaurantUpdateRequestToEntityConverter {

    public RestaurantEntity convert(final RestaurantUpdateRequest restaurantUpdateRequest ,
                                    final RestaurantEntity restaurantExisted){

        if (Objects.isNull(restaurantUpdateRequest)){
            throw new EntityNotFoundException("Not found restaurant update request");
        }

        if (Objects.isNull(restaurantExisted)){
            throw new EntityNotFoundException("Not found existing restaurant record with given id");
        }

        if(Objects.nonNull(restaurantUpdateRequest.getStatus())) {
            restaurantExisted.setStatus(restaurantUpdateRequest.getStatus());
        }

        return restaurantExisted;
    }

}
