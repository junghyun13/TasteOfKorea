package com.example.tasteofkorea.controller;

import com.example.tasteofkorea.dto.RecipeDTO;
import com.example.tasteofkorea.service.FastApiService;
import com.example.tasteofkorea.service.RecipeService;
import com.example.tasteofkorea.service.RecommenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;
@CrossOrigin(origins = "http://localhost:5173") // React 앱의 주소

@RestController
@RequestMapping("/api/food")
public class FoodController {

    @Autowired
    private FastApiService fastApiService;

    // FastAPI와 연결하여 이미지 예측
    @PostMapping("/predict")
    public ResponseEntity<Map<String, Object>> predictFood(@RequestParam("file") MultipartFile file) {
        try {
            Map<String, Object> result = fastApiService.predict(file);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            // 예외를 콘솔에 출력하여 더 자세한 정보를 얻을 수 있습니다.
            e.printStackTrace();
            return ResponseEntity.status(500).body(Map.of("error", "Failed to process the image"));
        }
    }

    @Autowired
    private RecipeService recipeService;

    // 특정 음식 조회
    @GetMapping("/{id}")
    public ResponseEntity<RecipeDTO> getRecipeById(@PathVariable("id") int id) {
        RecipeDTO recipe = recipeService.getRecipeById(id);
        if (recipe != null) {
            return ResponseEntity.ok(recipe);
        } else {
            return ResponseEntity.notFound().build();  // 404 Not Found
        }
    }

    @Autowired
    private RecommenderService recommenderService;
    // Recommend food items based on user preferences (in the format of a map)
    @PostMapping("/recommend")
    public ResponseEntity<List<RecipeDTO>> recommendFood(@RequestBody Map<String, Integer> userPreferences) {
        List<RecipeDTO> recommendedRecipes = recommenderService.recommendFood(userPreferences);
        return ResponseEntity.ok(recommendedRecipes);
    }


}
