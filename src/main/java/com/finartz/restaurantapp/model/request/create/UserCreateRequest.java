package com.finartz.restaurantapp.model.request.create;

import com.finartz.restaurantapp.model.dto.AddressDto;
import com.finartz.restaurantapp.model.enumerated.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserCreateRequest {

    private String email;

    private String password;

    private String name;

    private Role role = Role.USER;

    private AddressDto address;

}
