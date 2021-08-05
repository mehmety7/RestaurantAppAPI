package com.finartz.restaurantapp.model.request;

import com.finartz.restaurantapp.model.dto.BranchDto;
import com.finartz.restaurantapp.model.dto.CityDto;
import com.finartz.restaurantapp.model.dto.CountyDto;
import com.finartz.restaurantapp.model.dto.UserDto;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AddressRequest {

    private Long id;

    private String name;

    private CityDto city;

    private CountyDto county;

    private String district;

    private String otherContent;

    private Boolean enable;

    private UserDto user;

    private BranchDto branch;

}


