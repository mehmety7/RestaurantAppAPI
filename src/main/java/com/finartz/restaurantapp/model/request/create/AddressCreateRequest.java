package com.finartz.restaurantapp.model.request.create;

import com.finartz.restaurantapp.model.dto.BranchDto;
import com.finartz.restaurantapp.model.dto.CityDto;
import com.finartz.restaurantapp.model.dto.CountyDto;
import com.finartz.restaurantapp.model.dto.UserDto;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@Builder
public class AddressCreateRequest {

    @NotNull(message = "Address name may not be null")
    private String name;

    @NotNull(message = "Address city may not be null")
    private CityDto city;

    @NotNull(message = "Address county may not be null")
    private CountyDto county;

    @NotNull(message = "Address district may not be null")
    private String district;

    @NotNull(message = "Address otherContent may not be null")
    private String otherContent;

    private UserDto user;

    private BranchDto branch;

}


