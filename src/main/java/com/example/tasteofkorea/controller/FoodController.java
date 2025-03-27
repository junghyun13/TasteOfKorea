package com.example.tasteofkorea.controller;

import com.example.tasteofkorea.service.FastApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

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
}
