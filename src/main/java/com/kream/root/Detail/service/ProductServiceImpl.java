package com.kream.root.Detail.service;

import com.kream.root.Detail.dto.OneProductDTO;
//import com.kream.root.pro_detail.repository.ProductImgRepository;
//import com.kream.root.pro_detail.repository.ProductRepository;
import com.kream.root.Detail.dto.PriceChartDTO;
import com.kream.root.Detail.repository.PriceChartRepository;
import com.kream.root.Detail.repository.ProductDetailRepository;
import com.kream.root.Detail.repository.ProductImgDetailRepository;
import com.kream.root.MainAndShop.domain.Product;
import com.kream.root.MainAndShop.domain.ProductImg;
import com.kream.root.entity.OrderItems;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
@Transactional
public class ProductServiceImpl implements ProductService{
    private final ProductDetailRepository productDetailRepository;
    private final PriceChartRepository priceChartRepository;

    @Override
    public List<OneProductDTO> getProductDetail(Long prId) {
        List<OneProductDTO> productDetail = productDetailRepository.getProductDetail(prId);

        return productDetail;
    }

    @Override
    public List<OneProductDTO> getProductsByBrand(Long prId) {
        List<OneProductDTO> productsByBrand  = productDetailRepository.getProductsByBrand(prId);

        return productsByBrand;
    }

    @Override
    public List<OneProductDTO> getProductsByGender(Long prId) {
        List<OneProductDTO> productsByGender  = productDetailRepository.getProductsByGender(prId);

        return productsByGender;
    }

    @Override
    public List<OneProductDTO> getProductsByPrId(List<Long> recentProductsIds) {
        List<OneProductDTO> result = productDetailRepository.productsRecentView(recentProductsIds);

        return result;
    }
}