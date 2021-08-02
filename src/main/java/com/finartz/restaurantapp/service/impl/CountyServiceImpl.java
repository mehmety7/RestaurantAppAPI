package com.finartz.restaurantapp.service.impl;

import com.finartz.restaurantapp.model.County;
import com.finartz.restaurantapp.repository.CountyRepository;
import com.finartz.restaurantapp.service.CountyService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountyServiceImpl implements CountyService {

    private final CountyRepository countyRepository;

    public CountyServiceImpl(CountyRepository countyRepository) {
        this.countyRepository = countyRepository;
    }

    @Override
    public County create(County county){
        County save = countyRepository.save(county);
        return save;
    }

    @Override
    public List<County> getAll(){
        List<County> counties = countyRepository.findAll();
        return counties;
    }

    @Override
    public County getById(Long id){
        County county = countyRepository.getById(id);
        return county;
    }

    @Override
    public County update(County county){
        County foundCounty = countyRepository.getById(county.getId());

        if(county.getName() != null)
            foundCounty.setName(county.getName());
        if(county.getCity() != null)
            foundCounty.setCity(county.getCity());

        return countyRepository.save(foundCounty);

    }

    @Override
    public County deleteById(Long id){
        County county = countyRepository.getById(id);
        if (county != null) {
            countyRepository.deleteById(id);
            return county;
        }
        return county;
    }
}
