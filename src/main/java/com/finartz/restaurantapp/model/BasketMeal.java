package com.finartz.restaurantapp.model;

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
@Table(name = "basket_meals")
public class BasketMeal extends BaseDTO{

    @EmbeddedId
    private BasketMealKey id;

    @ManyToOne
    @MapsId("basketId")
    @JoinColumn(name = "basket_id")
    private Basket basket;

    @ManyToOne
    @MapsId("mealId")
    @JoinColumn(name = "meal_id")
    private Meal meal;

    private int count;
}
