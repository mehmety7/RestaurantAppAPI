package com.finartz.restaurantapp.model.entity;

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
@Table(name = "comments")
public class CommentEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String comment;
    @Column(name = "taste_point")
    private Integer tastePoint;
    @Column(name = "speed_point")
    private Integer speedPoint;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private UserEntity userEntity;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "branch_id", referencedColumnName = "id", nullable = false)
    private BranchEntity branchEntity;
}
