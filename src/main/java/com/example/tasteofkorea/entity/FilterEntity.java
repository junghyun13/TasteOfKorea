package com.example.tasteofkorea.entity;



import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "filter")
@Getter
@Setter
public class FilterEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // MySQL AUTO_INCREMENT와 매칭
    private Long id;

    @Column(name = "food_filter", length = 255)
    private String foodFilter;
}
