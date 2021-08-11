package com.finartz.restaurantapp.model.converter.entityconverter.fromCreateRequest;

import com.finartz.restaurantapp.model.converter.GenericConverter;
import com.finartz.restaurantapp.model.dto.AddressDto;
import com.finartz.restaurantapp.model.dto.MenuDto;
import com.finartz.restaurantapp.model.dto.RestaurantDto;
import com.finartz.restaurantapp.model.entity.AddressEntity;
import com.finartz.restaurantapp.model.entity.BranchEntity;
import com.finartz.restaurantapp.model.entity.MenuEntity;
import com.finartz.restaurantapp.model.entity.RestaurantEntity;
import com.finartz.restaurantapp.model.request.create.BranchCreateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BranchCreateRequestToEntityConverter implements GenericConverter<BranchCreateRequest, BranchEntity> {

    private final GenericConverter<MenuDto, MenuEntity> menuEntityConverter;
    private final GenericConverter<RestaurantDto, RestaurantEntity> restaurantEntityConverter;
    private final GenericConverter<AddressDto, AddressEntity> addressEntityConverter;

    @Override
    public BranchEntity convert(final BranchCreateRequest branchCreateRequest){
        if(branchCreateRequest == null){
            return null;
        }

        BranchEntity branchEntity = new BranchEntity();

        branchEntity.setName(branchCreateRequest.getName());
        branchEntity.setStatus(branchCreateRequest.getStatus());
        branchEntity.setMenuEntity(convert(branchCreateRequest.getMenu()));
        branchEntity.setRestaurantEntity(convert(branchCreateRequest.getRestaurant()));
        branchEntity.setAddressEntity(convert(branchCreateRequest.getAddress()));



        return branchEntity;
    }

    private AddressEntity convert(final AddressDto address){
        return addressEntityConverter.convert(address);
    }

    private RestaurantEntity convert(final RestaurantDto restaurant){
        return restaurantEntityConverter.convert(restaurant);
    }

    private MenuEntity convert(final MenuDto menu){
        return menuEntityConverter.convert(menu);
    }

}
