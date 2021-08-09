package com.finartz.restaurantapp.model.converter.entity.fromUpdateRequest;

import com.finartz.restaurantapp.model.converter.entity.fromDto.BranchDtoToEntityConverter;
import com.finartz.restaurantapp.model.dto.BranchDto;
import com.finartz.restaurantapp.model.entity.BranchEntity;
import com.finartz.restaurantapp.model.entity.RestaurantEntity;
import com.finartz.restaurantapp.model.request.update.RestaurantUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class RestaurantUpdateRequestToEntityConverter {

    private final BranchDtoToEntityConverter branchDtoToEntityConverter;

    public RestaurantEntity convert(final RestaurantUpdateRequest restaurantUpdateRequest ,
                                    final RestaurantEntity restaurantExisted){
        if (restaurantUpdateRequest == null)
            return null;

        if(restaurantUpdateRequest.getStatus() != null)
            restaurantExisted.setStatus(restaurantUpdateRequest.getStatus());

        if(restaurantUpdateRequest.getBranches() != null){
            List<BranchEntity> branchEntities = new ArrayList<>();
            restaurantUpdateRequest.getBranches().forEach(branch -> {
                branchEntities.add(convert(branch));
            });
            restaurantExisted.setBranchEntities(branchEntities);
        }

        return restaurantExisted;
    }

    private BranchEntity convert(final BranchDto branch){
        return branchDtoToEntityConverter.convert(branch);
    }


}
