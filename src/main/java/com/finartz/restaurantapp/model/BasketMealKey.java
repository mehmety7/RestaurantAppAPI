package com.finartz.restaurantapp.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Embeddable
public class BasketMealKey extends BaseDTO{

    @Column(name = "basket_id")
    private Long basketId;

    @Column(name= "meal_id")
    private Long mealId;

}
