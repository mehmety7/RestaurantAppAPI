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
@Table(name = "meals")
public class Meal extends BaseDTO{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Float price;

    @ManyToOne
    @JoinColumn(name = "menu_id", referencedColumnName = "id", nullable = false)
    private Menu menu;

    @ManyToMany()
    @JoinTable(name = "meal_item"
            , joinColumns = @JoinColumn(name = "meal_id", referencedColumnName = "id", nullable = false)
            , inverseJoinColumns = @JoinColumn(name = "item_id", referencedColumnName = "id", nullable = false))
    private List<Item> itemList;

    @OneToMany(mappedBy = "meal")
    private List<BasketMeal> basketMealList;

}
