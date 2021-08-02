package com.finartz.restaurantapp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.finartz.restaurantapp.model.enumerated.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
@Table(name="users")
public class User extends BaseDTO{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String email;
    private String password;
    private String name;

    // EnumType.ORDINAL --> STRING saves as a VARCHAR, HOWEVER ORDINAL saves as a INT with INDEX of role string.
    // In ORDINAL type has a mapping issue when add a new role in the middle or rearrange the enum's order.

//    @Enumerated(EnumType.STRING)
//    private Role role;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "id"))
    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private List<Role> roles;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<Address> addressList;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<Restaurant> restaurantList;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<CreditCard> creditCardList;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<Comment> commentList;


}
