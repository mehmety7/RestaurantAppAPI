package com.finartz.restaurantapp.model;

import com.finartz.restaurantapp.model.enumerated.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="branchs")
public class Branch extends BaseDTO{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id", referencedColumnName = "id", nullable = false)
    private Restaurant restaurant;

    @OneToOne(mappedBy = "branch")
    private Menu menu;

    @OneToOne(mappedBy = "branch", fetch = FetchType.LAZY)
    private Address address;

}
