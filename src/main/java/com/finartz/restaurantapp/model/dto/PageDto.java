package com.finartz.restaurantapp.model.dto;

import lombok.*;

import java.io.Serializable;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PageDto<T extends Serializable> extends BaseDto {

    private List<T> response;

    private Integer totalCount;

    private Integer pageCount;

}
