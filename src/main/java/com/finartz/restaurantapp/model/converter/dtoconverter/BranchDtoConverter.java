package com.finartz.restaurantapp.model.converter.dtoconverter;

import com.finartz.restaurantapp.model.converter.GenericConverter;
import com.finartz.restaurantapp.model.dto.BranchDto;
import com.finartz.restaurantapp.model.dto.MenuDto;
import com.finartz.restaurantapp.model.dto.RestaurantDto;
import com.finartz.restaurantapp.model.entity.BranchEntity;
import com.finartz.restaurantapp.model.entity.MenuEntity;
import com.finartz.restaurantapp.model.entity.RestaurantEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BranchDtoConverter implements GenericConverter<BranchEntity, BranchDto> {

    private final GenericConverter<MenuEntity, MenuDto> menuDtoConverter;
//    private final GenericConverter<AddressEntity, AddressDto> addressDtoConverter;
    private final GenericConverter<RestaurantEntity, RestaurantDto> restaurantDtoConverter;


    @Override
    public BranchDto convert(final BranchEntity branchEntity){
        if(branchEntity == null){
            return null;
        }

        BranchDto branchDto = new BranchDto();

        branchDto.setId(branchEntity.getId());
        branchDto.setName(branchEntity.getName());
        branchDto.setMenu(convert(branchEntity.getMenuEntity()));
        branchDto.setRestaurant(convert(branchEntity.getRestaurantEntity()));
//        branchDto.setAddress(convert(branchEntity.getAddressEntity()));

        return branchDto;
    }

    private MenuDto convert(final MenuEntity menuEntity){
        return menuDtoConverter.convert(menuEntity);
    }

    private RestaurantDto convert(final RestaurantEntity restaurantEntity){
        return restaurantDtoConverter.convert(restaurantEntity);
    }
//    private AddressDto convert(final AddressEntity addressEntity){
//        return addressDtoConverter.convert(addressEntity);
//    }

}
