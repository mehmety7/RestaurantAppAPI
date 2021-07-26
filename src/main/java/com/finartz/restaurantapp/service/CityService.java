package com.finartz.restaurantapp.service;

import com.finartz.restaurantapp.model.City;
import com.finartz.restaurantapp.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityService {

    @Autowired
    private CityRepository cityRepository;

    public City create(City city){
        City save = cityRepository.save(city);
        return save;
    }

    public List<City> getAll(){
        List<City> cities = cityRepository.findAll();
        return cities;
    }

    public City getById(Long id){
        City city = cityRepository.getById(id);
        return city;
    }

    public City update(City city){
        City update = cityRepository.getById(city.getId());
        if(update != null) {
            cityRepository.save(city);
            return update;
        }
        return city;
    }

    public City deleteById(Long id){
        City city = cityRepository.getById(id);
        if (city != null) {
            cityRepository.deleteById(id);
            return city;
        }
        return city;
    }
}
