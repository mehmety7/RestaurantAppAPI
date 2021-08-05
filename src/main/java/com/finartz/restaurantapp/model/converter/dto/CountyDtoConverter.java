package com.finartz.restaurantapp.model.converter.dto;

import com.finartz.restaurantapp.model.converter.GenericConverter;
import com.finartz.restaurantapp.model.dto.CityDto;
import com.finartz.restaurantapp.model.dto.CountyDto;
import com.finartz.restaurantapp.model.entity.CityEntity;
import com.finartz.restaurantapp.model.entity.CountyEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CountyDtoConverter implements GenericConverter<CountyEntity, CountyDto> {

    private final GenericConverter<CityEntity, CityDto> cityDtoConverter;

    @Override
    public CountyDto convert(final CountyEntity countyEntity){
        if(countyEntity == null){
            return null;
        }

        CountyDto countyDto = new CountyDto();

        countyDto.setId(countyEntity.getId());
        countyDto.setName(countyEntity.getName());
        countyDto.setCity(convert(countyEntity.getCityEntity()));

        return countyDto;
    }

    private CityDto convert(final CityEntity cityEntity){
        return cityDtoConverter.convert(cityEntity);
    }

}
