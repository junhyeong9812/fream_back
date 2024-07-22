package com.kream.root.MainAndShop.repository;

import com.kream.root.MainAndShop.domain.ProductImg;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductImgRepository extends JpaRepository<ProductImg, Long> {
    Optional<ProductImg> findByPrid(long prId);

}
