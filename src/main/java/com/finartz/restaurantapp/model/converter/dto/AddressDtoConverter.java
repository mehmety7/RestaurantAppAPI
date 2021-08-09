package com.finartz.restaurantapp.model.converter.dto;

import com.finartz.restaurantapp.model.converter.GenericConverter;
import com.finartz.restaurantapp.model.dto.*;
import com.finartz.restaurantapp.model.entity.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class AddressDtoConverter implements GenericConverter<AddressEntity, AddressDto> {

    private final GenericConverter<CityEntity, CityDto> cityDtoConverter;
    private final GenericConverter<CountyEntity, CountyDto> countyDtoConverter;
    private final GenericConverter<UserEntity, UserDto> userDtoConverter;
    private final GenericConverter<BranchEntity, BranchDto> branchDtoConverter;

    @Override
    public AddressDto convert(final AddressEntity addressEntity){
        if (addressEntity == null){
            return null;
        }

        AddressDto address = new AddressDto();

        address.setId(addressEntity.getId());
        address.setName(addressEntity.getName());
        address.setCity(convert(addressEntity.getCityEntity()));
        address.setCounty(convert(addressEntity.getCountyEntity()));
        address.setDistrict(addressEntity.getDistrict());
        address.setOtherContent(addressEntity.getOtherContent());
        address.setEnable(addressEntity.getEnable());
        address.setUser(convert(addressEntity.getUserEntity()));
        address.setBranch(convert(addressEntity.getBranchEntity()));

        return address;

    }

    private CityDto convert(final CityEntity cityEntity){
        return cityDtoConverter.convert(cityEntity);
    }

    private CountyDto convert(final CountyEntity countyEntity){
        return countyDtoConverter.convert(countyEntity);
    }

    private UserDto convert(final UserEntity userEntity){
        return userDtoConverter.convert(userEntity);
    }

    private BranchDto convert(final BranchEntity branchEntity){
        return branchDtoConverter.convert(branchEntity);
    }

}
