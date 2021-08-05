package com.finartz.restaurantapp.model.entity;

import com.finartz.restaurantapp.model.enumerated.Status;
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

    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id", referencedColumnName = "id", nullable = false)
    private RestaurantEntity restaurantEntity;

    @OneToOne(mappedBy = "branchEntity")
    private MenuEntity menuEntity;

    @OneToOne(mappedBy = "branchEntity", fetch = FetchType.LAZY)
    private AddressEntity addressEntity;

}
