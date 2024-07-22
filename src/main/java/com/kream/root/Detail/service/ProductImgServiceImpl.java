package com.kream.root.Detail.service;

import com.kream.root.MainAndShop.domain.ProductImg;
import com.kream.root.Detail.dto.ProductImgDTO;
import com.kream.root.Detail.repository.ProductImgDetailRepository;//체크 필요
//import com.kream.root.pro_detail.repository.ProductImgRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class ProductImgServiceImpl implements ProductImgService {
    private final ProductImgDetailRepository productImgRepository;
    private final ModelMapper modelMapper;

    @Override
    public ProductImgDTO getImg(long prIid) {
        Optional<ProductImg> result = productImgRepository.findById(prIid);
        ProductImg productImg = result.orElseThrow();

        //Entity -> DTO
        return modelMapper.map(productImg, ProductImgDTO.class);
    }
}