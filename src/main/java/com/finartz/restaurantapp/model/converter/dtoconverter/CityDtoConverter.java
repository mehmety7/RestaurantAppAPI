package com.finartz.restaurantapp.model.converter.dtoconverter;

import com.finartz.restaurantapp.model.converter.GenericConverter;
import com.finartz.restaurantapp.model.dto.CityDto;
import com.finartz.restaurantapp.model.dto.CountyDto;
import com.finartz.restaurantapp.model.entity.CityEntity;
import com.finartz.restaurantapp.model.entity.CountyEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class CityDtoConverter implements GenericConverter<CityEntity, CityDto> {

    private final GenericConverter<CountyEntity, CountyDto> countyDtoConverter;

    @Override
    public CityDto convert(final CityEntity cityEntity){
        if(cityEntity == null){
            return null;
        }

        CityDto cityDto = new CityDto();

        cityDto.setId(cityEntity.getId());
        cityDto.setName(cityEntity.getName());

        List<CountyDto> counties = new ArrayList<>();
        if(cityEntity.getCountyEntities() != null) {
            cityEntity.getCountyEntities().forEach(countyEntity -> {
                counties.add(convert(countyEntity));
            });
        }
        cityDto.setCounties(counties);

        return cityDto;
    }

    private CountyDto convert(final CountyEntity countyEntity){
        return countyDtoConverter.convert(countyEntity);
    }

}
