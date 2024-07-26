package com.kream.root.Adjustment;

import com.kream.root.MainAndShop.domain.Product;
import com.kream.root.entity.PriceHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface PriceHistoryRepository extends JpaRepository<PriceHistory, Long> {
    PriceHistory findTopByProductAndHistoryDateBeforeOrderByHistoryDateDesc(Product product, LocalDate date);

    List<PriceHistory> findByProductAndHistoryDateBetween(Product product, LocalDate startDate, LocalDate endDate);
}
