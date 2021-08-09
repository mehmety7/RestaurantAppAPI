package com.finartz.restaurantapp.model.request.update;

import com.finartz.restaurantapp.model.dto.BranchDto;
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
public class RestaurantUpdateRequest {

    private Status status = Status.WAITING;

    private List<BranchDto> branches;

}
