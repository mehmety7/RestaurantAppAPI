package com.finartz.restaurantapp.model.converter.dto;

import com.finartz.restaurantapp.model.converter.GenericConverter;
import com.finartz.restaurantapp.model.dto.CityDto;
import com.finartz.restaurantapp.model.entity.CityEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CityDtoConverter implements GenericConverter<CityEntity, CityDto> {

//    private final GenericConverter<CountyEntity, CountyDto> countyDtoConverter;

    @Override
    public CityDto convert(final CityEntity cityEntity){
        if(cityEntity == null){
            return null;
        }

        CityDto cityDto = new CityDto();

        cityDto.setId(cityEntity.getId());
        cityDto.setName(cityEntity.getName());

//        List<CountyDto> counties = new ArrayList<>();
//        cityEntity.getCountyEntities().forEach(countyEntity -> {
//            counties.add(convert(countyEntity));
//        });
//
//        cityDto.setCounties(counties);

        return cityDto;
    }

//    private CountyDto convert(final CountyEntity countyEntity){
//        return countyDtoConverter.convert(countyEntity);
//    }

}
