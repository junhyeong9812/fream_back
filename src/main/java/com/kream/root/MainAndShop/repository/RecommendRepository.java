package com.kream.root.MainAndShop.repository;

import com.kream.root.MainAndShop.repository.ProductRecommend.ProductRecommend;
import com.kream.root.entity.Recommend;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecommendRepository extends JpaRepository <Recommend, Long>, ProductRecommend {
}
