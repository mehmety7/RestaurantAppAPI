package com.finartz.restaurantapp.model.request.create;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.finartz.restaurantapp.model.enumerated.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserCreateRequest {

    @NotNull(message = "User email may not be null")
    private String email;

    @NotNull(message = "User password may not be null")
    private String password;

    @NotNull(message = "User name may not be null")
    private String name;

    private Role role = Role.USER;

    @JsonAlias(value = "address")
    @NotNull(message = "User address may not be null")
    private AddressCreateRequest addressCreateRequest;

}
