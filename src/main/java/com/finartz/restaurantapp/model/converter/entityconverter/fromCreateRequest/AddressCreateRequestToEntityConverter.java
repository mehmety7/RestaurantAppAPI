package com.finartz.restaurantapp.model.converter.entityconverter.fromCreateRequest;

import com.finartz.restaurantapp.model.converter.GenericConverter;
import com.finartz.restaurantapp.model.dto.BranchDto;
import com.finartz.restaurantapp.model.dto.CityDto;
import com.finartz.restaurantapp.model.dto.CountyDto;
import com.finartz.restaurantapp.model.dto.UserDto;
import com.finartz.restaurantapp.model.entity.*;
import com.finartz.restaurantapp.model.request.create.AddressCreateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AddressCreateRequestToEntityConverter implements GenericConverter<AddressCreateRequest, AddressEntity> {

    private final GenericConverter<CityDto, CityEntity> cityEntityConverter;
    private final GenericConverter<CountyDto, CountyEntity> countyEntityConverter;
    private final GenericConverter<UserDto, UserEntity> userEntityConverter;
    private final GenericConverter<BranchDto, BranchEntity> branchEntityConverter;

    @Override
    public AddressEntity convert(final AddressCreateRequest addressCreateRequest) {
        if(addressCreateRequest == null){
            return null;
        }

        AddressEntity addressEntity = new AddressEntity();

        addressEntity.setName(addressCreateRequest.getName());
        addressEntity.setDistrict(addressCreateRequest.getDistrict());
        addressEntity.setOtherContent(addressCreateRequest.getOtherContent());
        addressEntity.setCityEntity(convert(addressCreateRequest.getCity()));
        addressEntity.setCountyEntity(convert(addressCreateRequest.getCounty()));
        addressEntity.setUserEntity(convert(addressCreateRequest.getUser()));
        addressEntity.setBranchEntity(convert(addressCreateRequest.getBranch()));

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
