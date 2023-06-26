package com.finartz.restaurantapp.model.dto;

import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CountyDto extends BaseDto {

    private Long id;

    private String name;

    private Long cityId;

}
