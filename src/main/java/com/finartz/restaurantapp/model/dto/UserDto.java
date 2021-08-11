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
public class UserDto {

    private Long id;

    private String email;

    private String name;

    private List<Role> roles;


//    private String password;
//    private List<AddressDto> addresses;
//    private List<RestaurantDto> restaurants;
//    private List<CommentDto> comments;

}
