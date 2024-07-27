package com.kream.root.Detail.repository.productDetail;

import com.kream.root.Detail.dto.OneProductDTO;

import java.time.LocalDate;
import java.util.List;

public interface Detail {
    //상세 전체 데이터
    List<OneProductDTO> getProductDetail(Long prId);

    //동일한 브랜드의 다른 상품 데이터
    List<OneProductDTO> getProductsByBrand(Long prId);

    //동일한 성별의 다른 상품 데이터
    List<OneProductDTO> getProductsByGender(Long prId);

    //최근 본 상품 리스트
    List<OneProductDTO> productsRecentView(List<Long> prId);
}