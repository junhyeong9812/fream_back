package com.kream.root.MainAndShop.service;

import com.kream.root.MainAndShop.dto.OneProductDTO;
import com.kream.root.MainAndShop.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Log4j2
@RequiredArgsConstructor
public class shopServiceImpl implements shopService{

    public final ProductRepository productRepository;

    @Override
    public List<OneProductDTO> getList() {
        List<OneProductDTO> pList = productRepository.totalList();
        // 전체 성공
        pList.forEach(data -> log.info(data));

        return pList;
    }
}
