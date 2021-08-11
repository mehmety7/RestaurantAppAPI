package com.finartz.restaurantapp.model.converter.entityconverter.fromUpdateRequest;

import com.finartz.restaurantapp.model.entity.RestaurantEntity;
import com.finartz.restaurantapp.model.request.update.RestaurantUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RestaurantUpdateRequestToEntityConverter {

    public RestaurantEntity convert(final RestaurantUpdateRequest restaurantUpdateRequest ,
                                    final RestaurantEntity restaurantExisted){
        if (restaurantUpdateRequest == null)
            return null;

        if(restaurantUpdateRequest.getStatus() != null)
            restaurantExisted.setStatus(restaurantUpdateRequest.getStatus());

        return restaurantExisted;
    }

}
