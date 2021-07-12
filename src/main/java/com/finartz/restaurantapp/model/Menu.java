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
@Table(name = "menus")
public class Menu extends BaseDTO{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "branch_id", referencedColumnName = "id", nullable = false)
    private Branch branch;

    @OneToMany(mappedBy = "menu", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Meal> mealList;

}
