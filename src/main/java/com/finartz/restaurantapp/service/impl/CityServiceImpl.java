package com.finartz.restaurantapp.service.impl;

import com.finartz.restaurantapp.exception.EntityNotFoundException;
import com.finartz.restaurantapp.model.converter.dtoconverter.CityDtoConverter;
import com.finartz.restaurantapp.model.dto.CityDto;
import com.finartz.restaurantapp.model.entity.CityEntity;
import com.finartz.restaurantapp.repository.CityRepository;
import com.finartz.restaurantapp.service.CityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CityServiceImpl implements CityService {

    private final CityRepository cityRepository;

    private final CityDtoConverter cityDtoConverter;

    @Override
    public CityDto getCity(Long id){
        return cityDtoConverter.convert(cityRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Not found City with id:" + id)
        ));
    }

    @Override
    public List<CityDto> getCities(){
        List<CityEntity> cityEntities = cityRepository.findAll();
        List<CityDto> cities = new ArrayList<>();
        cityEntities.forEach(cityEntity -> {
            cities.add(cityDtoConverter.convert(cityEntity));
        });
        return cities;
    }


}

