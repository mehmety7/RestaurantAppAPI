package com.finartz.restaurantapp.model.request.update;

import com.finartz.restaurantapp.model.request.BaseRequest;
import lombok.*;

import javax.validation.constraints.NotNull;

@EqualsAndHashCode(callSuper = true)
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
