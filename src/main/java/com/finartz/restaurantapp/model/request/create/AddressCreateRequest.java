package com.finartz.restaurantapp.model.request.create;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@Builder
public class AddressCreateRequest {

    @NotNull(message = "Address name may not be null")
    private String name;

    @NotNull(message = "Address city may not be null")
    private Long cityId;

    @NotNull(message = "Address county may not be null")
    private Long countyId;

    @NotNull(message = "Address district may not be null")
    private String district;

    @NotNull(message = "Address otherContent may not be null")
    private String otherContent;

    private Long userId;

    private Long branchId;

}


