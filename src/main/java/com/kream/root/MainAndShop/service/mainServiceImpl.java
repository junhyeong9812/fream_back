package com.kream.root.MainAndShop.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kream.root.Detail.repository.UserBigDataRepository;
import com.kream.root.MainAndShop.domain.ProductPreData;
import com.kream.root.MainAndShop.dto.OneProductDTO;
import com.kream.root.MainAndShop.dto.RecommendDTO;
import com.kream.root.MainAndShop.dto.brandDTO;
import com.kream.root.MainAndShop.repository.ProductPredataRepository;
import com.kream.root.MainAndShop.repository.ProductRepository;
import com.kream.root.Wish.repository.WishRepository;
import com.kream.root.entity.Orders;
import com.kream.root.entity.Style;
import com.kream.root.entity.UserBigData;
import com.kream.root.order.repository.OrdersRepository;
import com.kream.root.style.repository.StyleRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.*;

@Service
@Log4j2
@RequiredArgsConstructor
public class mainServiceImpl implements mainService {

    private final ProductRepository productRepository;
    private final ProductPredataRepository productPredataRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<brandDTO> brandList() { // 브랜드 리스트 전달
//        Set<BrandMapping> brandLs = mainRepository.findAllByBrandIsNotNull();

        List<brandDTO> brandLs = productRepository.getBrandCnt();

        //여기서 Top 10 상품만 가져옴 --추후에 상의 해서 상품 몇개인지 파악하기
        List<brandDTO> top10 = new ArrayList<>(10);
        for (int i = 0 ; i < 10 ; i++){
            top10.add(brandLs.get(i));
        }

        top10.forEach(data -> log.info("brandSet : {}", data));

        return top10;
    }

    @Override
    public List<OneProductDTO> topProductList(List<Long> pridList) {
//        List<Long> pridList = LongStream.rangeClosed(1L, 10L).boxed().collect(Collectors.toList());

        List<OneProductDTO> oneProduct = productRepository.getOneProduct(pridList);

        return oneProduct;
    }
    @Override
    @Transactional
    public List<String> getRecommendList() throws JsonProcessingException {
        List<ProductPreData> result = productPredataRepository.findAll();
//        List< ProductPreDataDTO > PList = new ArrayList<>();
//        result.forEach(rep -> {
//            ProductPreDataDTO dto =  modelMapper.map(rep, ProductPreDataDTO.class);
//            PList.add(dto);
//        });
        // 전체 성공
//        result.forEach(data -> log.info(data));

        RestTemplate restTemplate = new RestTemplate();
        ObjectMapper mapper = new ObjectMapper();

        //헤더를 JSON으로 설정함
        HttpHeaders headers = new HttpHeaders();

        //파라미터로 들어온 dto를 JSON 객체로 변환
        headers.setContentType(MediaType.APPLICATION_JSON);

        String param = mapper.writeValueAsString(result);

        HttpEntity<String> entity = new HttpEntity<String>(param , headers);

        //실제 Flask 서버랑 연결하기 위한 URL
        String url = "http://127.0.0.1:5000/recommendSys";

        //Flask 서버로 데이터를 전송하고 받은 응답 값을 return
        return restTemplate.postForObject(url, entity, List.class);
    }

    @Autowired
    OrdersRepository ordersRepository;

    @Autowired
    UserBigDataRepository userBigDataRepository;

    @Autowired
    StyleRepository styleRepository;

    @Autowired
    WishRepository wishRepository;
    // 데이터 넣기
    // 데이터 합치기
    public List<RecommendDTO> createRecommendData(LocalDateTime date){
        List<Orders> orderList = ordersRepository.findByOrderDate(date);
        List<UserBigData> clickList = userBigDataRepository.findByUbDate(date);
        List<Style> styleList = styleRepository.findByStyleDate(date);







        return null;
    }


}
