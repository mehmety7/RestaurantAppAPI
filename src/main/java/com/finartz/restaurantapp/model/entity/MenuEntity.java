package com.finartz.restaurantapp.model.entity;

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
@Table(name = "menus")
public class MenuEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "branch_id", referencedColumnName = "id", nullable = false)
    private BranchEntity branchEntity;

    @OneToMany(mappedBy = "menuEntity")
    private List<MealEntity> mealEntities;

}
