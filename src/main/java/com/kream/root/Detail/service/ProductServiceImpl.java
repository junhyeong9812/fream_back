package com.kream.root.Detail.service;

import com.kream.root.Detail.dto.OneProductDTO;
//import com.kream.root.pro_detail.repository.ProductImgRepository;
//import com.kream.root.pro_detail.repository.ProductRepository;
import com.kream.root.Detail.repository.ProductDetailRepository;
import com.kream.root.Detail.repository.ProductImgDetailRepository;
import com.kream.root.MainAndShop.domain.Product;
import com.kream.root.MainAndShop.domain.ProductImg;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
@Transactional
public class ProductServiceImpl implements ProductService{
    private final ProductDetailRepository productDetailRepository;
    //private final ProductImgDetailRepository productImgDetailRepository;
    //private final ModelMapper modelMapper;

    @Override
    public List<OneProductDTO> getProductDetail(long prId) {
        List<OneProductDTO> productDetail = productDetailRepository.getProductDetail(prId);
        //Product product = result.orElseThrow();
        //Entity -> DTO
        //OneProductDTO productDTO = modelMapper.map(product, OneProductDTO.class);

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
        System.out.println("result:" + result);
        return result;
    }
}