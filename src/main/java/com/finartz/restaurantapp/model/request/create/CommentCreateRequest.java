package com.finartz.restaurantapp.model.request.create;

import com.finartz.restaurantapp.model.dto.BranchDto;
import com.finartz.restaurantapp.model.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.repository.NoRepositoryBean;

@Data
@Builder
@NoRepositoryBean
@AllArgsConstructor
public class CommentCreateRequest {

    private String comment;

    private Integer tastePoint;

    private Integer speedPoint;

    private UserDto user;

    private BranchDto branch;

}
