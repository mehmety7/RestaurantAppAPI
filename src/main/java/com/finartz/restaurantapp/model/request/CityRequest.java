package com.finartz.restaurantapp.model.request;

import com.finartz.restaurantapp.model.dto.CountyDto;
import lombok.Data;

import java.util.List;

@Data
public class CityRequest {

    private Long id;

    private String name;

    private List<CountyDto> counties;

}
