package com.example.tasteofkorea.repository;

import com.example.tasteofkorea.entity.FilterNumEntity;
import com.example.tasteofkorea.entity.FoodFilterListEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FoodFilterListRepository extends JpaRepository<FoodFilterListEntity, Long> {
    // 필요시 커스텀 쿼리 메서드 추가 가능

}
