package com.kream.root.Detail.repository;

import com.kream.root.MainAndShop.domain.ProductImg;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductImgDetailRepository extends JpaRepository<ProductImg, Long> {
    Optional<ProductImg> findByPriid(long priid);
}
