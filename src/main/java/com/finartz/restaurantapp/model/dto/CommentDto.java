package com.finartz.restaurantapp.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommentDto {

    private Long id;

    private String comment;

    private Integer tastePoint;

    private Integer speedPoint;

    private UserDto user;

    private BranchDto branch;

}
