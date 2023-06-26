package com.finartz.restaurantapp.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CityDto extends BaseDto {

    private Long id;

    private String name;

    @JsonInclude(value = JsonInclude.Include.NON_NULL) // If it is null then do not show as json value
    private List<CountyDto> counties;

}
