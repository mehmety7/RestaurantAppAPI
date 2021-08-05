package com.finartz.restaurantapp.model.converter.entity;

import com.finartz.restaurantapp.model.converter.GenericConverter;
import com.finartz.restaurantapp.model.dto.BranchDto;
import com.finartz.restaurantapp.model.dto.CityDto;
import com.finartz.restaurantapp.model.dto.CountyDto;
import com.finartz.restaurantapp.model.dto.UserDto;
import com.finartz.restaurantapp.model.entity.*;
import com.finartz.restaurantapp.model.request.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AddressRequestToEntityConverter implements GenericConverter<AddressRequest, AddressEntity> {

    private final GenericConverter<CityDto, CityEntity> cityEntityConverter;
    private final GenericConverter<CountyDto, CountyEntity> countyEntityConverter;
    private final GenericConverter<UserDto, UserEntity> userEntityConverter;
    private final GenericConverter<BranchDto, BranchEntity> branchEntityConverter;

    @Override
    public AddressEntity convert(final AddressRequest addressRequest) {
        if(addressRequest == null){
            return null;
        }

        AddressEntity addressEntity = new AddressEntity();

        addressEntity.setId(addressRequest.getId());
        addressEntity.setName(addressRequest.getName());
        addressEntity.setDistrict(addressRequest.getDistrict());
        addressEntity.setOtherContent(addressRequest.getOtherContent());
        addressEntity.setEnable(addressRequest.getEnable());
        addressEntity.setCityEntity(convert(addressRequest.getCity()));
        addressEntity.setCountyEntity(convert(addressRequest.getCounty()));
        addressEntity.setUserEntity(convert(addressRequest.getUser()));
        addressEntity.setBranchEntity(convert(addressRequest.getBranch()));

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
