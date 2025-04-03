package com.example.tasteofkorea.dto;

public class RecipeDTO {

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

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getKoreanName() {
        return koreanName;
    }

    public void setKoreanName(String koreanName) {
        this.koreanName = koreanName;
    }

    public String getRomanizedName() {
        return romanizedName;
    }

    public void setRomanizedName(String romanizedName) {
        this.romanizedName = romanizedName;
    }

    public String getEnglishName() {
        return englishName;
    }

    public void setEnglishName(String englishName) {
        this.englishName = englishName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getMadeWith() {
        return madeWith;
    }

    public void setMadeWith(String madeWith) {
        this.madeWith = madeWith;
    }

    public String getImgLink() {
        return imgLink;
    }

    public void setImgLink(String imgLink) {
        this.imgLink = imgLink;
    }

    public String getRecipeLink() {
        return recipeLink;
    }

    public void setRecipeLink(String recipeLink) {
        this.recipeLink = recipeLink;
    }

    public int getSpicy() {
        return spicy;
    }

    public void setSpicy(int spicy) {
        this.spicy = spicy;
    }

    public int getSour() {
        return sour;
    }

    public void setSour(int sour) {
        this.sour = sour;
    }

    public int getSalty() {
        return salty;
    }

    public void setSalty(int salty) {
        this.salty = salty;
    }

    public int getOily() {
        return oily;
    }

    public void setOily(int oily) {
        this.oily = oily;
    }

    public int getBigun() {
        return bigun;
    }

    public void setBigun(int bigun) {
        this.bigun = bigun;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }
}
