package com.finartz.restaurantapp.model.request.create;

import com.finartz.restaurantapp.model.dto.BranchDto;
import com.finartz.restaurantapp.model.dto.UserDto;
import com.finartz.restaurantapp.model.enumerated.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantCreateRequest {

    private String name;

    private Status status = Status.WAITING;

    private UserDto user;

    private List<BranchDto> branches;

}
