package com.kream.root.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.kream.root.MainAndShop.domain.Product;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "price_history")
@SequenceGenerator(
        name = "price_history_seq_generator",
        sequenceName = "price_history_seq",
        allocationSize = 1
)
public class PriceHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "price_history_seq_generator")
    @Column(name = "HISTORY_ID",nullable = false)
    private Long historyId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    @JsonBackReference("product-priceHistory")
    private Product product;

    @Column(name = "history_date", nullable = false)
    private LocalDate historyDate;

    // 변동 격차
    @Column(name = "price_Change",nullable = false)
    private double priceChange;

    // 변동 후의 가격
    @Column(name = "NEW_PRICE",nullable = false)
    private double newPrice;
}
