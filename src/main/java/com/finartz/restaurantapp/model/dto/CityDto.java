package com.finartz.restaurantapp.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CityDto {

    private Long id;

    private String name;

    @JsonInclude(value = JsonInclude.Include.NON_NULL) // If it is null then don't show as JSON value
    private List<CountyDto> counties;

}
