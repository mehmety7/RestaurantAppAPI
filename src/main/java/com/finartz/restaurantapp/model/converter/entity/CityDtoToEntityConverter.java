package com.finartz.restaurantapp.model.converter.entity;

import com.finartz.restaurantapp.model.converter.GenericConverter;
import com.finartz.restaurantapp.model.dto.CityDto;
import com.finartz.restaurantapp.model.entity.CityEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CityDtoToEntityConverter implements GenericConverter<CityDto, CityEntity> {

//    private final GenericConverter<CountyDto, CountyEntity> countyEntityConverter;

    @Override
    public CityEntity convert(final CityDto city){
        if(city == null){
            return null;
        }

        CityEntity cityEntity = new CityEntity();

        cityEntity.setId(city.getId());
        cityEntity.setName(city.getName());

//        List<CountyEntity> countyEntities = new ArrayList<>();
//        city.getCounties().forEach(county -> {
//            countyEntities.add(convert(county));
//        });
//        cityEntity.setCountyEntities(countyEntities);

        return cityEntity;
    }

//    private CountyEntity convert(final CountyDto county){
//        return countyEntityConverter.convert(county);
//    }

}
