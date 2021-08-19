package com.finartz.restaurantapp.model.converter.entityconverter.fromUpdateRequest;

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
        if (Objects.isNull(restaurantUpdateRequest))
            return null;

        if(Objects.nonNull(restaurantUpdateRequest.getStatus()))
            restaurantExisted.setStatus(restaurantUpdateRequest.getStatus());

        return restaurantExisted;
    }

}
