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
    private Long id;

    @Column(name = "food_filter", length = 255)
    private String foodFilter;

    // 외래 키 매핑 (filter_num_id → filter_num.id)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "filter_num_id", foreignKey = @ForeignKey(name = "fk_filter_num"))
    private FilterNumEntity filterNum;
}