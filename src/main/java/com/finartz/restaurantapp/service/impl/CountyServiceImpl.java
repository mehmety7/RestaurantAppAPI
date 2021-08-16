package com.finartz.restaurantapp.service.impl;

import com.finartz.restaurantapp.exception.EntityNotFoundException;
import com.finartz.restaurantapp.model.converter.dtoconverter.CountyDtoConverter;
import com.finartz.restaurantapp.model.dto.CountyDto;
import com.finartz.restaurantapp.model.entity.CountyEntity;
import com.finartz.restaurantapp.repository.CountyRepository;
import com.finartz.restaurantapp.service.CountyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CountyServiceImpl implements CountyService {

    private final CountyRepository countyRepository;
    private final CountyDtoConverter countyDtoConverter;

    @Override
    public CountyDto getCounty(Long id){
        return countyDtoConverter.convert(countyRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Not found County with id: " + id)
        ));
    }

    @Override
    public List<CountyDto> getCounties(Long city_id) {
        List<CountyEntity> countyEntities = countyRepository.getCountyEntitiesByCityEntity_Id(city_id);
        List<CountyDto> counties = new ArrayList<>();
        countyEntities.forEach(countyEntity -> {
            counties.add(countyDtoConverter.convert(countyEntity));
        });
        return counties;
    }

}
