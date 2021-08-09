package com.finartz.restaurantapp.model.request.create;

import com.finartz.restaurantapp.model.dto.BranchDto;
import com.finartz.restaurantapp.model.dto.CityDto;
import com.finartz.restaurantapp.model.dto.CountyDto;
import com.finartz.restaurantapp.model.dto.UserDto;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AddressCreateRequest {

    private String name;

    private CityDto city;

    private CountyDto county;

    private String district;

    private String otherContent;

    private UserDto user;

    private BranchDto branch;

}


