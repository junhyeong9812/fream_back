package com.kream.root.MainAndShop.repository.ProductRecommend;

import com.kream.root.MainAndShop.dto.OneProductDTO;
import com.kream.root.MainAndShop.dto.Recommend.GenderAgeRecommendDTO;
import com.kream.root.MainAndShop.dto.brandDTO;

import java.time.LocalDate;
import java.util.List;

public interface ProductRecommend {
    //추천을 위한 간단한 연산 필요할 경우
    //브랜드 별 추천
    List<brandDTO> getBrandCnt();

    // 이미지와 상품데이터의 결합
    List<OneProductDTO> getOneProduct(List<Long> pridList);

     List<GenderAgeRecommendDTO> getRecommendData(LocalDate start, LocalDate end) ;

}
