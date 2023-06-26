package com.finartz.restaurantapp.model.request.get;

import com.finartz.restaurantapp.model.request.BaseRequest;
import lombok.*;

import javax.validation.constraints.NotNull;

@EqualsAndHashCode(callSuper = true)
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemPageGetRequest extends BaseRequest {

    @NotNull(message = "Page number may not be null")
    private Integer pageNo;

    @NotNull(message = "Page size may not be null")
    private Integer pageSize;

    @Builder.Default
    private String sortBy = "id";

}
