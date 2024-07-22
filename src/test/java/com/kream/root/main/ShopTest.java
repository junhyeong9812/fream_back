package com.kream.root.main;

import com.kream.root.MainAndShop.domain.Product;
import com.kream.root.MainAndShop.dto.OneProductDTO;
import com.kream.root.MainAndShop.repository.ProductRepository;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
@Log4j2
public class ShopTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    public void testAll(){
        List<Product> pList = productRepository.findAll();
        pList.forEach(data -> log.info(data));
    }
    @Test
    public void testAllWithCat(){


        List<OneProductDTO> pList = productRepository.totalList();
        // 전체 성공
        pList.forEach(data -> log.info(data));
    }


}
