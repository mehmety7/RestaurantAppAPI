package com.finartz.restaurantapp.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Optional;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageDto<T> {

    private Optional<T> response;
    private Integer totalCount;

}
