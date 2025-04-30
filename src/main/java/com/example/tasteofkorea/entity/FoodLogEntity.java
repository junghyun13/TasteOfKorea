package com.example.tasteofkorea.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Entity
@Table(name = "food_log")
@Getter
@Setter
public class FoodLogEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "access_time")
    private LocalDateTime accessTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "food_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_food_log"))
    private RecipeEntity food;
}
