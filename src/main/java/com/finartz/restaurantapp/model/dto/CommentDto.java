package com.finartz.restaurantapp.model.dto;

import lombok.Data;

@Data
public class CommentDto {

    private Long id;

    private String comment;

    private Integer tastePoint;

    private Integer speedPoint;

    private UserDto user;

    private BranchDto branch;

}
