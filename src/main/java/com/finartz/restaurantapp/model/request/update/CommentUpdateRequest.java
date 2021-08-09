package com.finartz.restaurantapp.model.request.update;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommentUpdateRequest {

    private String comment;

    private Integer tastePoint;

    private Integer speedPoint;

}
