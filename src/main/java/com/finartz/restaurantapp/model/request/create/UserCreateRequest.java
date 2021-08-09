package com.finartz.restaurantapp.model.request.create;

import com.finartz.restaurantapp.model.dto.AddressDto;
import com.finartz.restaurantapp.model.dto.CommentDto;
import com.finartz.restaurantapp.model.dto.RestaurantDto;
import com.finartz.restaurantapp.model.enumerated.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserCreateRequest {

    private String email;

    private String password;

    private String name;

    private List<Role> roles = Arrays.asList(Role.USER);

    private List<AddressDto> addresses;

    private List<RestaurantDto> restaurants;

    private List<CommentDto> comments;

}
