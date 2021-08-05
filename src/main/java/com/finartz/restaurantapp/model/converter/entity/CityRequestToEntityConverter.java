package com.finartz.restaurantapp.model.converter.entity;

import com.finartz.restaurantapp.model.converter.GenericConverter;
import com.finartz.restaurantapp.model.dto.CountyDto;
import com.finartz.restaurantapp.model.entity.CityEntity;
import com.finartz.restaurantapp.model.entity.CountyEntity;
import com.finartz.restaurantapp.model.request.CityRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class CityRequestToEntityConverter implements GenericConverter<CityRequest, CityEntity> {

    private final GenericConverter<CountyDto, CountyEntity> countyEntityConverter;

    @Override
    public CityEntity convert(final CityRequest cityRequest){
        if(cityRequest == null){
            return null;
        }

        CityEntity cityEntity = new CityEntity();

        cityEntity.setId(cityRequest.getId());
        cityEntity.setName(cityRequest.getName());

        List<CountyEntity> countyEntities = new ArrayList<>();
        cityRequest.getCounties().forEach(county -> {
            countyEntities.add(convert(county));
        });
        cityEntity.setCountyEntities(countyEntities);

        return cityEntity;
    }

    private CountyEntity convert(final CountyDto county){
        return countyEntityConverter.convert(county);
    }

}
