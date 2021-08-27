package com.finartz.restaurantapp.model.converter.dtoconverter;

import com.finartz.restaurantapp.model.converter.GenericConverter;
import com.finartz.restaurantapp.model.dto.AddressDto;
import com.finartz.restaurantapp.model.entity.AddressEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Objects;

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
        address.setDistrict(addressEntity.getDistrict());
        address.setOtherContent(addressEntity.getOtherContent());

        if(Objects.nonNull(addressEntity.getCityEntity())){
            address.setCityId(addressEntity.getCityEntity().getId());
            address.setCityName(addressEntity.getCityEntity().getName());
        }
        if(Objects.nonNull(addressEntity.getCountyEntity())) {
            address.setCountyId(addressEntity.getCountyEntity().getId());
            address.setCountyName(addressEntity.getCountyEntity().getName());
        }

        if(Objects.nonNull(addressEntity.getUserEntity())) {
            address.setUserId(addressEntity.getUserEntity().getId());
        }
        else {
            address.setUserId(null);
        }

        if(Objects.nonNull(addressEntity.getBranchEntity())) {
            address.setBranchId(addressEntity.getBranchEntity().getId());
        }
        else {
            address.setBranchId(null);
        }

        address.setEnable(addressEntity.getEnable());

        return address;

    }

}
