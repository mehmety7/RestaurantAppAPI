package com.finartz.restaurantapp.model;

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
@Table(name = "items")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(name = "unit_type")
    private String unitType;

    @ManyToMany(mappedBy = "itemList")
    private List<Meal> mealList;

    public Item(Long id, String name, String unitType) {
        this.id = id;
        this.name = name;
        this.unitType = unitType;
    }
}
