package com.example.tasteofkorea.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "recipes")
@Getter
@Setter
public class RecipeEntity {
    @Id
    private int id;
    private String koreanName;
    private String romanizedName;
    private String englishName;
    private String category;
    private String madeWith;
    private String imgLink;
    private String recipeLink;
    private int spicy;
    private int sour;
    private int salty;
    private int oily;
    private int bigun;
    private int calories;
}
