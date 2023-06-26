package com.finartz.restaurantapp.model.dto;

import lombok.*;

@EqualsAndHashCode(callSuper = true)
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
