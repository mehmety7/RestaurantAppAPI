package com.finartz.restaurantapp.model.request;

import com.finartz.restaurantapp.model.dto.CityDto;
import lombok.Data;

@Data
public class CountyRequest {

    private Long id;

    private String name;

    private CityDto city;


}
