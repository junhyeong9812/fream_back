package com.kream.root.main;

import com.kream.root.MainAndShop.domain.Product;
import com.kream.root.MainAndShop.domain.ProductImg;
import com.kream.root.MainAndShop.dto.OneProductDTO;
import com.kream.root.MainAndShop.dto.brandDTO;
import com.kream.root.MainAndShop.dto.productDTO;
import com.kream.root.MainAndShop.mapping.BrandMapping;
import com.kream.root.MainAndShop.repository.ProductRepository;
import com.kream.root.MainAndShop.repository.ProductImgRepository;
import com.kream.root.MainAndShop.service.mainService;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

@SpringBootTest
@Log4j2
public class mainTest {


    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductImgRepository productImgRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Test
     public void testSelect (){

        long prid = 100;
        Optional<Product> result = productRepository.findById(prid);

        log.info(result);
    }

    @Test
    public void testBrandList (){
//        String brand = "Nike";

        Set<BrandMapping> result = productRepository.findAllByBrandIsNotNull();

        result.forEach(brand -> log.info(brand.getBrand()));
    }

    @Autowired
    mainService ms ;

    @Test
    public void testServiceTest (){
//        String brand = "Nike";

        ms.brandList();

//        result.forEach(brand -> log.info(brand.getBrand()));
    }

    @Test
    public void testBrandCnt (){
//        String brand = "Nike";

        List<brandDTO> result = productRepository.getBrandCnt();

        result.forEach(map -> log.info("map : {}", map));
    }
    @Test
    public void testBrandCntService (){
//        String brand = "Nike";

        List<brandDTO> result = ms.brandList();

        result.forEach(map -> log.info("map : {}", map));
    }

    @Test
    public void testSelectAll(){
        List<productDTO> productDTOList = new ArrayList<>();
        LongStream.rangeClosed(1L, 10L).forEach(prid -> {

            Optional<Product> result = productRepository.findByPrid(prid);
            Product product = result.orElseThrow();
            log.info(product);
            productDTO dto = modelMapper.map(product, productDTO.class);
            productDTOList.add(dto);

        });

        productDTOList.forEach(data -> log.info(data));

    }


    @Test
    public void testImg(){
        Long id = 1L;
        Optional<ProductImg> result = productImgRepository.findByPrid(id);
        log.info(result);
    }

    @Test
    public void testProductAndImg(){
        List<Long> pridList = LongStream.rangeClosed(1L, 10L).boxed().collect(Collectors.toList());
        log.info(pridList);

        List<OneProductDTO> oneProduct = productRepository.getOneProduct(pridList);
        log.info(oneProduct);
        oneProduct.forEach(ls -> log.info("ls : {}", ls));

    }
}
