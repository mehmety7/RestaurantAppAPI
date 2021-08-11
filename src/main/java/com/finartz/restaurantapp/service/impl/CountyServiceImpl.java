package com.finartz.restaurantapp.service.impl;

import com.finartz.restaurantapp.model.converter.dtoconverter.CountyDtoConverter;
import com.finartz.restaurantapp.model.dto.CountyDto;
import com.finartz.restaurantapp.model.entity.CountyEntity;
import com.finartz.restaurantapp.repository.CountyRepository;
import com.finartz.restaurantapp.service.CountyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CountyServiceImpl implements CountyService {

    private final CountyRepository countyRepository;
    private final CountyDtoConverter countyDtoConverter;

    @Override
    public CountyDto getCounty(Long id){
        CountyEntity countyEntity = countyRepository.getById(id);
        return countyDtoConverter.convert(countyEntity);
    }

    @Override
    public CountyDto getCounty(String name, Long city_id) {
        CountyEntity countyEntity = countyRepository.getCountyEntityByNameAndCityEntity_Id(name, city_id);
        return countyDtoConverter.convert(countyEntity);
    }

}
