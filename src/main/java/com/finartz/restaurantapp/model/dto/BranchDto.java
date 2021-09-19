package com.finartz.restaurantapp.model.dto;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BranchDto extends BaseDto {

    private Long id;

    private String name;

    private Long menuId;

    private Long restaurantId;

}
