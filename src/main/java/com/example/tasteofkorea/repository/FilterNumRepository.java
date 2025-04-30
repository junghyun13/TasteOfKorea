package com.example.tasteofkorea.repository;
import com.example.tasteofkorea.entity.FilterNumEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FilterNumRepository extends JpaRepository<FilterNumEntity, Long> {
    // 필요 시 커스텀 쿼리 메서드 정의 가능
}
