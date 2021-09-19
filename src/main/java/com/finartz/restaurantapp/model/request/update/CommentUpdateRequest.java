package com.finartz.restaurantapp.model.request.update;

import com.finartz.restaurantapp.model.request.BaseRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommentUpdateRequest extends BaseRequest {

    @NotNull(message = "Restaurant id may not be null for updating")
    private Long id;

    private String comment;

    private Integer tastePoint;

    private Integer speedPoint;

}
