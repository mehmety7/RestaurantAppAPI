package com.finartz.restaurantapp.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CountyDto extends BaseDto {

    private Long id;

    private String name;

    private Long cityId;

}
