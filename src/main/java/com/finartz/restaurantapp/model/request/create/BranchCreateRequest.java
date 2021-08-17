package com.finartz.restaurantapp.model.request.create;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BranchCreateRequest {

    @NotNull(message = "Branch name may not be null")
    private String name;

    @NotNull(message = "Branch city may not be null")
    private Long restaurantId;

    @JsonAlias(value = "address")
    private AddressCreateRequest addressCreateRequest;

}
