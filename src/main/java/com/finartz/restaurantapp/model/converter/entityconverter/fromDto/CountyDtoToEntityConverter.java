package com.finartz.restaurantapp.model.converter.entityconverter.fromDto;

import com.finartz.restaurantapp.model.converter.GenericConverter;
import com.finartz.restaurantapp.model.dto.CountyDto;
import com.finartz.restaurantapp.model.entity.CountyEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CountyDtoToEntityConverter implements GenericConverter<CountyDto, CountyEntity> {

//    private final GenericConverter<CityDto, CityEntity> cityEntityConverter;

    @Override
    public CountyEntity convert(final CountyDto county){
        if(county == null){
            return null;
        }

        CountyEntity countyEntity = new CountyEntity();

        countyEntity.setId(county.getId());
        countyEntity.setName(county.getName());
//        countyEntity.setCityEntity(convert(county.getCity()));

        return countyEntity;
    }

//    private CityEntity convert(final CityDto city){
//        return cityEntityConverter.convert(city);
//    }

}