package com.kream.root.MainAndShop.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.kream.root.MainAndShop.dto.OneProductDTO;
import com.kream.root.MainAndShop.dto.brandDTO;

import java.util.List;

public interface mainService {

    //브랜드별 분류
    public List<brandDTO> brandList() ;
    public List<OneProductDTO> topProductList(List<Long> pridList);
    public List<String>  getRecommendList() throws JsonProcessingException;
}
