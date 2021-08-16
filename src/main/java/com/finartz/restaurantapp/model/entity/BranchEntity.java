package com.finartz.restaurantapp.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name="branches")
public class BranchEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id", referencedColumnName = "id", nullable = false)
    private RestaurantEntity restaurantEntity;

    @OneToOne(mappedBy = "branchEntity", cascade = CascadeType.ALL)
    private MenuEntity menuEntity;

    @OneToOne(mappedBy = "branchEntity", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private AddressEntity addressEntity;

}
