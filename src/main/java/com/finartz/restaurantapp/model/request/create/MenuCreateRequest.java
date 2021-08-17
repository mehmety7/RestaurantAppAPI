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
public class MenuCreateRequest {

    @NotNull(message = "Menu branch may not be null")
    private Long branchId;

}
