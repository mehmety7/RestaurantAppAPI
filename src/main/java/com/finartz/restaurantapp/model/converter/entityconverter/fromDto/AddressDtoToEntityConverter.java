package com.finartz.restaurantapp.model.converter.entityconverter.fromDto;

import com.finartz.restaurantapp.model.converter.GenericConverter;
import com.finartz.restaurantapp.model.dto.*;
import com.finartz.restaurantapp.model.entity.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AddressDtoToEntityConverter implements GenericConverter<AddressDto, AddressEntity> {

    private final GenericConverter<CityDto, CityEntity> cityEntityConverter;
    private final GenericConverter<CountyDto, CountyEntity> countyEntityConverter;
    private final GenericConverter<UserDto, UserEntity> userEntityConverter;
    private final GenericConverter<BranchDto, BranchEntity> branchEntityConverter;

    @Override
    public AddressEntity convert(final AddressDto address) {
        if(address == null){
            return null;
        }

        AddressEntity addressEntity = new AddressEntity();

        addressEntity.setId(address.getId());
        addressEntity.setName(address.getName());
        addressEntity.setDistrict(address.getDistrict());
        addressEntity.setOtherContent(address.getOtherContent());
//        addressEntity.setEnable(address.getEnable());
        addressEntity.setCityEntity(convert(address.getCity()));
        addressEntity.setCountyEntity(convert(address.getCounty()));
        addressEntity.setUserEntity(convert(address.getUser()));
        addressEntity.setBranchEntity(convert(address.getBranch()));

        return addressEntity;

    }

    private CityEntity convert(final CityDto city){
        return cityEntityConverter.convert(city);
    }

    private CountyEntity convert(final CountyDto county){
        return countyEntityConverter.convert(county);
    }

    private UserEntity convert(final UserDto user){
        return userEntityConverter.convert(user);
    }

    private BranchEntity convert(final BranchDto branch){
        return branchEntityConverter.convert(branch);
    }

}
