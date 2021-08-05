package com.finartz.restaurantapp.model.request;

import com.finartz.restaurantapp.model.dto.AddressDto;
import com.finartz.restaurantapp.model.dto.CommentDto;
import com.finartz.restaurantapp.model.dto.RestaurantDto;
import com.finartz.restaurantapp.model.enumerated.Role;
import lombok.Data;

import java.util.List;

@Data
public class UserRequest {

    private Long id;

    private String email;

    private String password;

    private String name;

    private List<Role> roles;

    private List<AddressDto> addresses;

    private List<RestaurantDto> restaurants;

    private List<CommentDto> comments;

}
