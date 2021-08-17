package com.finartz.restaurantapp.model.request.create;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ItemCreateRequest {

    @NotNull(message = "Item name may not be null")
    private String name;

    @NotNull(message = "Item unit type may not be null")
    private String unitType;

}
