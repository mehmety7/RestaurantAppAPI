package com.finartz.restaurantapp.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddressDto {

    private Long id;

    private String name;

    private Long cityId;

    private String cityName;

    private Long countyId;

    private String countyName;

    private String district;

    private String otherContent;

    private Long userId;

    private Long branchId;

//    private Boolean enable;

}
