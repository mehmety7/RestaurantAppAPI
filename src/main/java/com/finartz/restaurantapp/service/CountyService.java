package com.finartz.restaurantapp.service;

import com.finartz.restaurantapp.model.County;
import com.finartz.restaurantapp.repository.CountyRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountyService {

    private final CountyRepository countyRepository;

    public CountyService(CountyRepository countyRepository) {
        this.countyRepository = countyRepository;
    }

    public County create(County county){
        County save = countyRepository.save(county);
        return save;
    }

    public List<County> getAll(){
        List<County> counties = countyRepository.findAll();
        return counties;
    }

    public County getById(Long id){
        County county = countyRepository.getById(id);
        return county;
    }

    public County update(County county){
        County update = countyRepository.getById(county.getId());
        if(update != null) {
            countyRepository.save(county);
            return update;
        }
        return county;
    }

    public County deleteById(Long id){
        County county = countyRepository.getById(id);
        if (county != null) {
            countyRepository.deleteById(id);
            return county;
        }
        return county;
    }
}
