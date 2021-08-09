package com.finartz.restaurantapp.model.converter.entity.fromCreateRequest;

import com.finartz.restaurantapp.model.converter.GenericConverter;
import com.finartz.restaurantapp.model.dto.BranchDto;
import com.finartz.restaurantapp.model.dto.UserDto;
import com.finartz.restaurantapp.model.entity.BranchEntity;
import com.finartz.restaurantapp.model.entity.RestaurantEntity;
import com.finartz.restaurantapp.model.entity.UserEntity;
import com.finartz.restaurantapp.model.request.create.RestaurantCreateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class RestaurantCreateRequestToEntityConverter implements GenericConverter<RestaurantCreateRequest, RestaurantEntity> {

    private final GenericConverter<UserDto, UserEntity> userEntityConverter;
    private final GenericConverter<BranchDto, BranchEntity> branchEntityConverter;

    @Override
    public RestaurantEntity convert(final RestaurantCreateRequest restaurantCreateRequest){
        if(restaurantCreateRequest == null){
            return null;
        }

        RestaurantEntity restaurantEntity = new RestaurantEntity();

        restaurantEntity.setName(restaurantCreateRequest.getName());
        restaurantEntity.setStatus(restaurantCreateRequest.getStatus());
        restaurantEntity.setUserEntity(convert(restaurantCreateRequest.getUser()));

        List<BranchEntity> branchEntities = new ArrayList<>();
        restaurantCreateRequest.getBranches().forEach(branch -> {
            branchEntities.add(convert(branch));
        });
        restaurantEntity.setBranchEntities(branchEntities);

        return restaurantEntity;
    }

    private UserEntity convert(final UserDto user){
        return userEntityConverter.convert(user);
    }

    private BranchEntity convert(final BranchDto branch){
        return branchEntityConverter.convert(branch);
    }

}