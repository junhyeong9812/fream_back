package com.kream.root.Detail.service;

import com.kream.root.Detail.dto.OneProductDTO;

import java.util.List;

public interface ProductService {
    List<OneProductDTO> getProductDetail(Long prId);
    List<OneProductDTO> getProductsByBrand(Long prId);
    List<OneProductDTO> getProductsByGender(Long prId);
    List<OneProductDTO> getProductsByPrId(List<Long> recentProductsIds);
}