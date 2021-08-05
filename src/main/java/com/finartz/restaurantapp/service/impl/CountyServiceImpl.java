package com.finartz.restaurantapp.service.impl;

import com.finartz.restaurantapp.model.entity.CountyEntity;
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
    public List<CountyEntity> getCounties(){
        List<CountyEntity> counties = countyRepository.findAll();
        return counties;
    }

    @Override
    public CountyEntity getCounty(Long id){
        CountyEntity countyEntity = countyRepository.getById(id);
        return countyEntity;
    }

    @Override
    public CountyEntity createCounty(CountyEntity countyEntity){
        CountyEntity save = countyRepository.save(countyEntity);
        return save;
    }

    @Override
    public CountyEntity updateCounty(CountyEntity countyEntity){
        CountyEntity foundCountyEntity = countyRepository.getById(countyEntity.getId());

        if(countyEntity.getName() != null)
            foundCountyEntity.setName(countyEntity.getName());
        if(countyEntity.getCityEntity() != null)
            foundCountyEntity.setCityEntity(countyEntity.getCityEntity());

        return countyRepository.save(foundCountyEntity);

    }

    @Override
    public CountyEntity deleteCounty(Long id){
        CountyEntity countyEntity = countyRepository.getById(id);
        if (countyEntity != null) {
            countyRepository.deleteById(id);
            return countyEntity;
        }
        return countyEntity;
    }
}
