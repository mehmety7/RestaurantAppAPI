package com.finartz.restaurantapp.model.converter.dtoconverter;

import com.finartz.restaurantapp.exception.EntityNotFoundException;
import com.finartz.restaurantapp.model.converter.GenericConverter;
import com.finartz.restaurantapp.model.dto.CityDto;
import com.finartz.restaurantapp.model.dto.CountyDto;
import com.finartz.restaurantapp.model.entity.CityEntity;
import com.finartz.restaurantapp.model.entity.CountyEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class CityDtoConverter implements GenericConverter<CityEntity, CityDto> {

    private final GenericConverter<CountyEntity, CountyDto> countyDtoConverter;

    @Override
    public CityDto convert(final CityEntity cityEntity){
        if(Objects.isNull(cityEntity)){
            throw new EntityNotFoundException("Not found city entity");
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
