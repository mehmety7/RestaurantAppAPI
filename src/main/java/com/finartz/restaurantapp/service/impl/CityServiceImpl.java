package com.finartz.restaurantapp.service.impl;

import com.finartz.restaurantapp.model.City;
import com.finartz.restaurantapp.repository.CityRepository;
import com.finartz.restaurantapp.service.CityService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityServiceImpl implements CityService {

    private final CityRepository cityRepository;

    public CityServiceImpl(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    @Override
    public City create(City city){
        City save = cityRepository.save(city);
        return save;
    }

    @Override
    public List<City> getAll(){
        List<City> cities = cityRepository.findAll();
        return cities;
    }

    @Override
    public City getById(Long id){
        City city = cityRepository.getById(id);
        return city;
    }

    @Override
    public City update(City city){
        City foundCity = cityRepository.getById(city.getId());

        if(city.getName() != null)
            foundCity.setName(city.getName());
        if(city.getCountyList() != null)
            foundCity.setCountyList(city.getCountyList());

        return cityRepository.save(foundCity);
    }

    @Override
    public City deleteById(Long id){
        City city = cityRepository.getById(id);
        if (city != null) {
            cityRepository.deleteById(id);
            return city;
        }
        return city;
    }
}

