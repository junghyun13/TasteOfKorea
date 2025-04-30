package com.example.tasteofkorea.service;

import com.example.tasteofkorea.dto.FilterCriteria;
import com.example.tasteofkorea.dto.RecipeDTO;
import com.example.tasteofkorea.entity.FilterEntity;
import com.example.tasteofkorea.entity.FilterNumEntity;
import com.example.tasteofkorea.entity.FoodFilterListEntity;
import com.example.tasteofkorea.entity.RecipeEntity;
import com.example.tasteofkorea.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FoodFilterService {

    private final FoodFilterListRepository foodFilterListRepository;
    private final FilterNumRepository filterNumRepository;
    private final RecipeRepository recipeRepository;
    private final FoodLogRepository foodLogRepository; // FoodLogRepository 추가

    @Autowired
    public FoodFilterService(FoodFilterListRepository foodFilterListRepository,
                             FilterNumRepository filterNumRepository,
                             RecipeRepository recipeRepository,
                             FoodLogRepository foodLogRepository) {
        this.foodFilterListRepository = foodFilterListRepository;
        this.filterNumRepository = filterNumRepository;
        this.recipeRepository = recipeRepository;
        this.foodLogRepository = foodLogRepository; // FoodLogRepository 초기화
    }

    // 🚫 이전: List<RecipeEntity>
    // ✅ 변경: List<RecipeDTO>
    public List<RecipeDTO> getSafeFoods(FilterCriteria filterCriteria) {
        List<FoodFilterListEntity> allFoodFilters = foodFilterListRepository.findAll();

        // 필터링된 음식만 추출
        List<RecipeEntity> safeFoods = allFoodFilters.stream()
                .filter(foodFilter -> isSafeFood(foodFilter.getFilter().getFilterNum(), filterCriteria))
                .map(FoodFilterListEntity::getFood)
                .collect(Collectors.toList());

        // 음식 리스트를 조회 수를 기준으로 내림차순 정렬
        return safeFoods.stream()
                .sorted((foodA, foodB) -> {
                    // 조회 수가 없으면 0으로 취급
                    int countA = foodLogRepository.countByFoodId(foodA.getId());
                    int countB = foodLogRepository.countByFoodId(foodB.getId());
                    return Integer.compare(countB, countA); // 내림차순 정렬
                })
                .map(this::convertToDTO)  // DTO로 변환
                .collect(Collectors.toList());
    }

    private boolean isSafeFood(FilterNumEntity filterNumEntity, FilterCriteria filterCriteria) {
        return (filterCriteria.getEgg() == 0 || filterNumEntity.getEgg() == 0) &&
                (filterCriteria.getMilk() == 0 || filterNumEntity.getMilk() == 0) &&
                (filterCriteria.getBuckwheat() == 0 || filterNumEntity.getBuckwheat() == 0) &&
                (filterCriteria.getPeanut() == 0 || filterNumEntity.getPeanut() == 0) &&
                (filterCriteria.getSoybean() == 0 || filterNumEntity.getSoybean() == 0) &&
                (filterCriteria.getWheat() == 0 || filterNumEntity.getWheat() == 0) &&
                (filterCriteria.getFish() == 0 || filterNumEntity.getFish() == 0) &&
                (filterCriteria.getCrab() == 0 || filterNumEntity.getCrab() == 0) &&
                (filterCriteria.getShrimp() == 0 || filterNumEntity.getShrimp() == 0) &&
                (filterCriteria.getPork() == 0 || filterNumEntity.getPork() == 0) &&
                (filterCriteria.getPeach() == 0 || filterNumEntity.getPeach() == 0) &&
                (filterCriteria.getTomato() == 0 || filterNumEntity.getTomato() == 0) &&
                (filterCriteria.getSulfites() == 0 || filterNumEntity.getSulfites() == 0) &&
                (filterCriteria.getWalnut() == 0 || filterNumEntity.getWalnut() == 0) &&
                (filterCriteria.getChicken() == 0 || filterNumEntity.getChicken() == 0) &&
                (filterCriteria.getBeef() == 0 || filterNumEntity.getBeef() == 0) &&
                (filterCriteria.getSquid() == 0 || filterNumEntity.getSquid() == 0) &&
                (filterCriteria.getBivalvesAndAbalone() == 0 || filterNumEntity.getBivalvesAndAbalone() == 0) &&
                (filterCriteria.getPineNut() == 0 || filterNumEntity.getPineNut() == 0);
    }

    // ✅ RecipeEntity → RecipeDTO 변환
    private RecipeDTO convertToDTO(RecipeEntity entity) {
        RecipeDTO dto = new RecipeDTO();
        dto.setId(entity.getId());
        dto.setKoreanName(entity.getKoreanName());
        dto.setEnglishName(entity.getEnglishName());
        dto.setPronunciation(entity.getPronunciation());
        dto.setInformation(entity.getInformation());
        dto.setRecipeLink(entity.getRecipeLink());
        dto.setEatLink(entity.getEatLink());
        dto.setRecipeSource(entity.getRecipeSource());
        dto.setEatingSource(entity.getEatingSource());
        dto.setImageLink(entity.getImageLink());
        dto.setImageSource(entity.getImageSource());
        return dto;
    }
}
