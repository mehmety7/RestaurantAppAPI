package com.finartz.restaurantapp.model.converter.dtoconverter;

import com.finartz.restaurantapp.model.converter.GenericConverter;
import com.finartz.restaurantapp.model.dto.*;
import com.finartz.restaurantapp.model.entity.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AddressDtoConverter implements GenericConverter<AddressEntity, AddressDto> {

    @Override
    public AddressDto convert(final AddressEntity addressEntity){
        if (addressEntity == null){
            return null;
        }

        AddressDto address = new AddressDto();

        address.setId(addressEntity.getId());
        address.setName(addressEntity.getName());
        address.setCityId(addressEntity.getCityEntity().getId());
        address.setCityName(addressEntity.getCityEntity().getName());
        address.setCountyId(addressEntity.getCountyEntity().getId());
        address.setCountyName(addressEntity.getCountyEntity().getName());
        address.setDistrict(addressEntity.getDistrict());
        address.setOtherContent(addressEntity.getOtherContent());

        if(addressEntity.getUserEntity() != null)
            address.setUserId(addressEntity.getUserEntity().getId());
        else
            address.setUserId(null);

        if(addressEntity.getBranchEntity() != null)
            address.setBranchId(addressEntity.getBranchEntity().getId());
        else
            address.setBranchId(null);

//      address.setEnable(addressEntity.getEnable());

        return address;

    }

}
