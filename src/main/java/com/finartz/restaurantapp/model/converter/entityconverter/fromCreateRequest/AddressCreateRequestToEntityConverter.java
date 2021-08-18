package com.finartz.restaurantapp.model.converter.entityconverter.fromCreateRequest;

import com.finartz.restaurantapp.model.converter.GenericConverter;
import com.finartz.restaurantapp.model.entity.*;
import com.finartz.restaurantapp.model.request.create.AddressCreateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AddressCreateRequestToEntityConverter implements GenericConverter<AddressCreateRequest, AddressEntity> {

    @Override
    public AddressEntity convert(final AddressCreateRequest addressCreateRequest) {
        if(addressCreateRequest == null){
            return null;
        }

        AddressEntity addressEntity = new AddressEntity();

        addressEntity.setName(addressCreateRequest.getName());
        addressEntity.setDistrict(addressCreateRequest.getDistrict());
        addressEntity.setOtherContent(addressCreateRequest.getOtherContent());

        CityEntity cityEntity = new CityEntity();
        cityEntity.setId(addressCreateRequest.getCityId());
        addressEntity.setCityEntity(cityEntity);

        CountyEntity countyEntity = new CountyEntity();
        countyEntity.setId(addressCreateRequest.getCountyId());
        addressEntity.setCountyEntity(countyEntity);

        if(addressCreateRequest.getUserId() != null) {
            UserEntity userEntity = new UserEntity();
            userEntity.setId(addressCreateRequest.getUserId());
            addressEntity.setUserEntity(userEntity);
        } else if (addressCreateRequest.getBranchId() != null){
            BranchEntity branchEntity = new BranchEntity();
            branchEntity.setId(addressCreateRequest.getBranchId());
            addressEntity.setBranchEntity(branchEntity);
        } else {
            addressEntity.setBranchEntity(null);
            addressEntity.setUserEntity(null);
        }

        addressEntity.setEnable(true);

        return addressEntity;

    }

}
