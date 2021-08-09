package com.finartz.restaurantapp.model.request.update;

import com.finartz.restaurantapp.model.dto.MenuDto;
import com.finartz.restaurantapp.model.enumerated.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BranchUpdateRequest {

    private Status status;

    private MenuDto menu;

}
