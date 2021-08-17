package com.finartz.restaurantapp.model.converter.dtoconverter;

import com.finartz.restaurantapp.model.converter.GenericConverter;
import com.finartz.restaurantapp.model.dto.AddressDto;
import com.finartz.restaurantapp.model.entity.AddressEntity;
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
        if(addressEntity.getUserEntity() != null){
                address.setUserId(addressEntity.getUserEntity().getId());
            address.setBranchId(null);
        }else if(addressEntity.getBranchEntity() != null){
                address.setBranchId(addressEntity.getBranchEntity().getId());
            address.setUserId(null);
        }else{
                address.setBranchId(null);
            address.setUserId(null);
        }


//        address.setEnable(addressEntity.getEnable());

        return address;

    }

}
