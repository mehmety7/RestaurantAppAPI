package com.finartz.restaurantapp.model.converter.entityconverter.fromCreateRequest;

import com.finartz.restaurantapp.model.converter.GenericConverter;
import com.finartz.restaurantapp.model.entity.BranchEntity;
import com.finartz.restaurantapp.model.entity.RestaurantEntity;
import com.finartz.restaurantapp.model.request.create.BranchCreateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@RequiredArgsConstructor
public class BranchCreateRequestToEntityConverter implements GenericConverter<BranchCreateRequest, BranchEntity> {

    @Override
    public BranchEntity convert(final BranchCreateRequest branchCreateRequest){
        if(Objects.isNull(branchCreateRequest)){
            return null;
        }

        BranchEntity branchEntity = new BranchEntity();

        branchEntity.setName(branchCreateRequest.getName());

        RestaurantEntity restaurantEntity = new RestaurantEntity();
        if (Objects.nonNull(branchCreateRequest.getRestaurantId())){
            restaurantEntity.setId(branchCreateRequest.getRestaurantId());
        }
        branchEntity.setRestaurantEntity(restaurantEntity);
        branchEntity.setAddressEntity(null);

        return branchEntity;
    }
}
