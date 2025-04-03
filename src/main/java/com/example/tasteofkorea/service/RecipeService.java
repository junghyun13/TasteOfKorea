package com.example.tasteofkorea.service;

import com.example.tasteofkorea.dto.RecipeDTO;
import com.example.tasteofkorea.entity.RecipeEntity;
import com.example.tasteofkorea.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RecipeService {

    @Autowired
    private RecipeRepository recipeRepository;

    // 특정 음식 조회
    public RecipeDTO getRecipeById(int id) {
        Optional<RecipeEntity> recipeEntity = recipeRepository.findById(id);
        if (recipeEntity.isPresent()) {
            return convertToDTO(recipeEntity.get());
        } else {
            return null; // 혹은 예외를 던질 수도 있습니다
        }
    }

    private RecipeDTO convertToDTO(RecipeEntity entity) {
        RecipeDTO dto = new RecipeDTO();
        dto.setId(entity.getId());
        dto.setKoreanName(entity.getKoreanName());
        dto.setRomanizedName(entity.getRomanizedName());
        dto.setEnglishName(entity.getEnglishName());
        dto.setCategory(entity.getCategory());
        dto.setMadeWith(entity.getMadeWith());
        dto.setImgLink(entity.getImgLink());
        dto.setRecipeLink(entity.getRecipeLink());
        dto.setSpicy(entity.getSpicy());
        dto.setSour(entity.getSour());
        dto.setSalty(entity.getSalty());
        dto.setOily(entity.getOily());
        dto.setBigun(entity.getBigun());
        dto.setCalories(entity.getCalories());
        return dto;
    }
}
