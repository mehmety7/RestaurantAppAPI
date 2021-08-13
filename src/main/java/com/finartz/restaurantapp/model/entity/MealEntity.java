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
@Table(name = "meals")
public class MealEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Double price;

    @ManyToOne
    @JoinColumn(name = "menu_id", referencedColumnName = "id", nullable = false)
    private MenuEntity menuEntity;

    @ManyToMany
    @JoinTable(name = "meal_item"
            , joinColumns = @JoinColumn(name = "meal_id", referencedColumnName = "id", nullable = false)
            , inverseJoinColumns = @JoinColumn(name = "item_id", referencedColumnName = "id", nullable = false))
    private List<ItemEntity> itemEntities;

}
