package com.finartz.restaurantapp.model.converter.entityconverter.fromDto;

import com.finartz.restaurantapp.model.converter.GenericConverter;
import com.finartz.restaurantapp.model.dto.BranchDto;
import com.finartz.restaurantapp.model.dto.MenuDto;
import com.finartz.restaurantapp.model.entity.BranchEntity;
import com.finartz.restaurantapp.model.entity.MenuEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BranchDtoToEntityConverter implements GenericConverter<BranchDto, BranchEntity> {

    private final GenericConverter<MenuDto, MenuEntity> menuEntityConverter;

//    private final GenericConverter<RestaurantDto, RestaurantEntity> restaurantEntityConverter;
//    private final GenericConverter<AddressDto, AddressEntity> addressEntityConverter;

    @Override
    public BranchEntity convert(final BranchDto branch){
        if(branch == null){
            return null;
        }

        BranchEntity branchEntity = new BranchEntity();

        branchEntity.setId(branch.getId());
        branchEntity.setName(branch.getName());
        branchEntity.setStatus(branch.getStatus());
        branchEntity.setMenuEntity(convert(branch.getMenu()));


//      branchEntity.setRestaurantEntity(convert(branch.getRestaurant()));
//      branchEntity.setAddressEntity(convert(branch.getAddress()));

        return branchEntity;
    }

//    private RestaurantEntity convert(final RestaurantDto restaurant){
//        return restaurantEntityConverter.convert(restaurant);
//    }
//    private AddressEntity convert(final AddressDto address){
//        return addressEntityConverter.convert(address);
//    }
//
    private MenuEntity convert(final MenuDto menu){
        return menuEntityConverter.convert(menu);
    }

}
