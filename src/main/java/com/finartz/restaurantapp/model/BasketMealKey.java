package com.finartz.restaurantapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class BasketMealKey extends BaseDTO{

    @Column(name = "basket_id")
    private Long basketId;

    @Column(name= "meal_id")
    private Long mealId;

}
