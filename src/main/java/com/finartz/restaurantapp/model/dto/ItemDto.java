package com.finartz.restaurantapp.model.dto;

import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ItemDto extends BaseDto {

    private Long id;

    private String name;

    private String unitType;

}
