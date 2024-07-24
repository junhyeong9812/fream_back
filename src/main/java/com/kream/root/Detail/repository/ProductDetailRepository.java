package com.kream.root.Detail.repository;

import com.kream.root.Detail.dto.OneProductDTO;
import com.kream.root.MainAndShop.domain.Product;
import com.kream.root.Detail.repository.productDetail.Detail;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProductDetailRepository extends JpaRepository<Product, Long>, Detail{
    Optional<Product> findByPrid(long prid);
}
