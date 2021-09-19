package com.finartz.restaurantapp.model.dto;

import com.finartz.restaurantapp.model.enumerated.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto extends BaseDto {

    private Long id;

    private String email;

    private String name;

    private List<Role> roles;

}
