package com.finartz.restaurantapp.model.request;

import com.finartz.restaurantapp.model.dto.BranchDto;
import com.finartz.restaurantapp.model.dto.UserDto;
import lombok.Data;

@Data
public class CommentRequest {

    private Long id;

    private String comment;

    private Integer tastePoint;

    private Integer speedPoint;

    private UserDto user;

    private BranchDto branch;

}
