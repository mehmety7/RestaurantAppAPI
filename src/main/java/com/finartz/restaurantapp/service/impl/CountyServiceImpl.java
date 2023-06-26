package com.finartz.restaurantapp.service.impl;

import com.finartz.restaurantapp.exception.EntityNotFoundException;
import com.finartz.restaurantapp.model.converter.dtoconverter.CountyDtoConverter;
import com.finartz.restaurantapp.model.dto.CountyDto;
import com.finartz.restaurantapp.model.entity.CountyEntity;
import com.finartz.restaurantapp.repository.CountyRepository;
import com.finartz.restaurantapp.service.CountyService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@CacheConfig(cacheNames = {"counties"})
public class CountyServiceImpl implements CountyService {

    private final CountyRepository countyRepository;
    private final CountyDtoConverter countyDtoConverter;

    @Cacheable
    @Override
    public CountyDto getCounty(Long id){
        return countyDtoConverter.convert(countyRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Not found County with id: " + id)
        ));
    }

    @Cacheable
    @Override
    public List<CountyDto> getCounties(Long cityId) {
        List<CountyEntity> countyEntities = countyRepository.getCountyEntitiesByCityEntityId(cityId);
        List<CountyDto> counties = new ArrayList<>();
        countyEntities.forEach(countyEntity -> counties.add(countyDtoConverter.convert(countyEntity)));
        return counties;
    }

}
