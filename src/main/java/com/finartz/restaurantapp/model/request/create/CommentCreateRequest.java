package com.finartz.restaurantapp.model.request.create;

import com.finartz.restaurantapp.model.dto.BranchDto;
import com.finartz.restaurantapp.model.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.repository.NoRepositoryBean;

import javax.validation.constraints.NotNull;

@Data
@Builder
@NoRepositoryBean
@AllArgsConstructor
public class CommentCreateRequest {

    @NotNull(message = "Comment comment may not be null")
    private String comment;

    @NotNull(message = "Comment taste point may not be null")
    private Integer tastePoint;

    @NotNull(message = "Comment speed point may not be null")
    private Integer speedPoint;

    @NotNull(message = "Comment user may not be null")
    private UserDto user;

    @NotNull(message = "Comment branch may not be null")
    private BranchDto branch;

}
