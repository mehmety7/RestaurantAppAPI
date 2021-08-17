package com.finartz.restaurantapp.model.request.create;

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

    private String comment = "";

    @NotNull(message = "Comment taste point may not be null")
    private Integer tastePoint;

    @NotNull(message = "Comment speed point may not be null")
    private Integer speedPoint;

    @NotNull(message = "Comment user may not be null")
    private Long userId;

    @NotNull(message = "Comment branch may not be null")
    private Long branchId;

}
