package com.finartz.restaurantapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="addresses")
public class Address extends BaseDTO{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "city_id", referencedColumnName = "id", nullable = false)
    private City city;

    @OneToOne
    @JoinColumn(name = "county_id", referencedColumnName = "id", nullable = false)
    private County county;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "branch_id", referencedColumnName = "id")
    private Branch branch;

    private String district;
    private String other_content;
}
