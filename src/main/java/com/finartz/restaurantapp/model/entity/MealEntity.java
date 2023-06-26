package com.finartz.restaurantapp.model.entity;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@ToString
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
    @ToString.Exclude
    private List<ItemEntity> itemEntities;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        MealEntity that = (MealEntity) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
