package com.finartz.restaurantapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "items")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(name = "unit_type")
    private String unitType;

    @ManyToMany(mappedBy = "itemList", fetch = FetchType.EAGER) // item bazında arama yapılınca meal'lar goruntulenebilsin
    private List<Meal> mealList;

}
