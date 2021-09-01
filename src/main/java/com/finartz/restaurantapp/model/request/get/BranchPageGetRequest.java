package com.finartz.restaurantapp.model.request.get;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BranchPageGetRequest {

    @NotNull(message = "Page number may not be null")
    private Integer pageNo;

    @NotNull(message = "Page size may not be null")
    private Integer pageSize;

    private String sortBy = "id";

    @NotNull(message = "County id may not be null")
    private Long countyId;

}
