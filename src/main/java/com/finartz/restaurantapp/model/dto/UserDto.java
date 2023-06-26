package com.finartz.restaurantapp.model.dto;

import com.finartz.restaurantapp.model.enumerated.Role;
import lombok.*;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
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
