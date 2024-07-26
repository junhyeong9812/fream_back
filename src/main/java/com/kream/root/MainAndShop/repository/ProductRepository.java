package com.kream.root.MainAndShop.repository;

import com.kream.root.MainAndShop.mapping.BrandMapping;
import com.kream.root.MainAndShop.domain.Product;
import com.kream.root.MainAndShop.repository.ProductCategory.Category;
import com.kream.root.MainAndShop.repository.ProductRecommend.ProductRecommend;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.Set;


public interface ProductRepository extends JpaRepository<Product, Long>, ProductRecommend, Category {

    Set<BrandMapping> findAllByBrandIsNotNull(); // 브랜드명 가져오기 -- 컬럼명 잘 지키기

    Optional<Product> findByPrid(long prid) ;


}
