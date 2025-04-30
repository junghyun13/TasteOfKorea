package com.example.tasteofkorea.dto;

public class RecipeDTO {
    private Long id;
    private String koreanName;
    private String englishName;
    private String pronunciation;
    private String information;
    private String recipeLink;
    private String eatLink;
    private String recipeSource;     // 추가
    private String eatingSource;     // 추가
    private String imageLink;        // 추가
    private String imageSource;      // 추가

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getKoreanName() {
        return koreanName;
    }

    public void setKoreanName(String koreanName) {
        this.koreanName = koreanName;
    }

    public String getEnglishName() {
        return englishName;
    }

    public void setEnglishName(String englishName) {
        this.englishName = englishName;
    }

    public String getPronunciation() {
        return pronunciation;
    }

    public void setPronunciation(String pronunciation) {
        this.pronunciation = pronunciation;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public String getRecipeLink() {
        return recipeLink;
    }

    public void setRecipeLink(String recipeLink) {
        this.recipeLink = recipeLink;
    }

    public String getEatLink() {
        return eatLink;
    }

    public void setEatLink(String eatLink) {
        this.eatLink = eatLink;
    }

    public String getRecipeSource() {
        return recipeSource;
    }

    public void setRecipeSource(String recipeSource) {
        this.recipeSource = recipeSource;
    }

    public String getEatingSource() {
        return eatingSource;
    }

    public void setEatingSource(String eatingSource) {
        this.eatingSource = eatingSource;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    public String getImageSource() {
        return imageSource;
    }

    public void setImageSource(String imageSource) {
        this.imageSource = imageSource;
    }
}
