package com.kream.root.MainAndShop.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kream.root.Detail.repository.UserBigDataRepository;
import com.kream.root.MainAndShop.dto.OneProductDTO;
import com.kream.root.MainAndShop.dto.Recommend.GenderAgeRequestFlaskDTO;
import com.kream.root.MainAndShop.dto.Recommend.GenderAgeRecommendDTO;
import com.kream.root.MainAndShop.dto.Recommend.GenderRequestFlaskDTO;
import com.kream.root.MainAndShop.dto.brandDTO;
import com.kream.root.MainAndShop.repository.ProductRepository;
import com.kream.root.MainAndShop.repository.RecommendRepository;
import com.kream.root.entity.*;
import com.kream.root.order.repository.OrdersRepository;
import com.kream.root.style.repository.StyleRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
@Log4j2
@RequiredArgsConstructor
public class mainServiceImpl implements mainService {

    private final ProductRepository productRepository;

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
    public List<String> getRecommendList(int age, String gender) throws JsonProcessingException {
        LocalDate end = LocalDate.now().minusDays(1);
        LocalDate start = end.minusDays(15);

        log.info(start);
        log.info(end);

        List<GenderAgeRecommendDTO> total_data = recommendRepository.getRecommendData(start, end);
        GenderAgeRequestFlaskDTO result = new GenderAgeRequestFlaskDTO(gender, age, total_data);

        // 전체 성공
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
        return restTemplate.postForObject(url, entity, List.class); //이거는 return으로 내보낼 PRID 값들이라 LIst가 맞음
    }

    @Autowired
    OrdersRepository ordersRepository;

    @Autowired
    UserBigDataRepository userBigDataRepository;

    @Autowired
    StyleRepository styleRepository;

    @Autowired
    RecommendRepository recommendRepository;



    @Override
    @Transactional
    public void createRecommendTable(LocalDate date){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime startDate = LocalDateTime.parse(date.toString() + " 00:00:00", formatter);
        LocalDateTime endDate = LocalDateTime.parse(date + " 23:59:59", formatter);

        List<Orders> orderList = ordersRepository.findByOrderDateBetween(startDate, endDate);
        List<UserBigData> clickList = userBigDataRepository.findByUbDate(date);
        List<Style> styleList = styleRepository.findByStyleDateBetween(startDate, endDate);

//        List<Recommend> builderList = new ArrayList<>();

        if (!orderList.isEmpty()) {
            orderList.stream().map(Orders::getOrderItems).forEach(orderItems -> {
                orderItems.forEach(items -> {
                    List<UserBigData> toClickRemove = clickList.stream()
                            .filter(clickData ->
                                    clickData.getUserListDTO().getUlid() == items.getOrder().getUser().getUlid() &&
                                            Objects.equals(clickData.getProduct().getPrid(), items.getProduct().getPrid()))
                            .toList();

                    int clickCount = toClickRemove.stream()
                            .mapToInt(UserBigData::getUb_clickCount)
                            .sum();

                    clickList.removeAll(toClickRemove);

                    List<Style> toStyleRemove = styleList.stream()
                            .filter(style ->
                                    style.getUser().getUlid() == items.getOrder().getUser().getUlid() &&
                                            Objects.equals(style.getProduct().getPrid(), items.getProduct().getPrid()))
                            .toList();

                    int styleCount = toStyleRemove.size();
                    styleList.removeAll(toStyleRemove);


                    Recommend recommendBuilder = Recommend.builder()
                            .recommend_date(date)
                            .user(items.getOrder().getUser())
                            .product(items.getProduct())
                            .quantity(items.getQuantity())
                            .click(clickCount)
                            .style(styleCount)
                            .build();

                    log.info("order builder : {}", recommendBuilder);

                    recommendRepository.save(recommendBuilder);
//                    builderList.add(recommendBuilder);
                });
            });
        }
        Set<Map<String, Object>> clickInfoSet = new HashSet<>();
        if (!clickList.isEmpty()) {
            clickList.forEach(click -> {
                int ulid = click.getUserListDTO().getUlid();
                Long prid = click.getProduct().getPrid();

                Map<String, Object> info = new HashMap<>();
                info.put("ulid", ulid);
                info.put("prid", prid);


                if (!clickInfoSet.contains(info)) {
                    clickInfoSet.add(info);
                    List<UserBigData> toClickRemove = clickList.stream()
                            .filter(clickData ->
                                    clickData.getUserListDTO().getUlid() == click.getUserListDTO().getUlid() &&
                                            Objects.equals(clickData.getProduct().getPrid(), click.getProduct().getPrid()))
                            .toList();

                    int clickCount = toClickRemove.stream()
                            .mapToInt(UserBigData::getUb_clickCount)
                            .sum();

                List<Style> toStyleRemove = styleList.stream()
                        .filter(style ->
                                style.getUser().getUlid() == click.getUserListDTO().getUlid() &&
                                        Objects.equals(style.getProduct().getPrid(), click.getProduct().getPrid()))
                        .toList();

                int styleCount = toStyleRemove.size();
                styleList.removeAll(toStyleRemove);

                Recommend recommendBuilder = Recommend.builder()
                        .recommend_date(date)
                        .user(click.getUserListDTO())
                        .product(click.getProduct())
                        .quantity(0)
                        .click(clickCount)
                        .style(styleCount)
                        .build();

                log.info("click builder : {}", recommendBuilder);

                recommendRepository.save(recommendBuilder);
                } else {
                    return;
                }
//                builderList.add(recommendBuilder);
            });
        }
        Set<Map<String, Object>> styleInfoSet = new HashSet<>();
        if (!styleList.isEmpty()) {
            styleList.forEach(style -> {

                int ulid = style.getUser().getUlid();
                Long prid = style.getProduct().getPrid();

                Map<String, Object> info = new HashMap<>();
                info.put("ulid", ulid);
                info.put("prid", prid);

                if (!styleInfoSet.contains(info)) {
                    styleInfoSet.add(info);
                    log.info("styleData");
                    List<Style> toStyleRemove = styleList.stream()
                            .filter(styleData ->
                                    styleData.getUser().getUlid() == styleData.getUser().getUlid() &&
                                            Objects.equals(styleData.getProduct().getPrid(), styleData.getProduct().getPrid()))
                            .toList();

                    int styleCount = toStyleRemove.size();

                    Recommend recommendBuilder = Recommend.builder()
                            .recommend_date(date)
                            .user(style.getUser())
                            .product(style.getProduct())
                            .quantity(0)
                            .click(0)
                            .style(styleCount)
                            .build();

                    log.info("style builder : {}", recommendBuilder);

                    recommendRepository.save(recommendBuilder);
                }
                else {
                    return; //continue
                }
//                builderList.add(recommendBuilder);
            });
        }
    }
    @Override
    @Transactional
    public List<String> getGenderRecommendList(String gender) throws JsonProcessingException {
        LocalDate end = LocalDate.now().minusDays(1);
        LocalDate start = end.minusDays(15);

        log.info(start);
        log.info(end);
        log.info("gender : " + gender.toLowerCase());

        List<GenderAgeRecommendDTO> total_data = recommendRepository.getRecommendData(start, end);
        GenderRequestFlaskDTO result = new GenderRequestFlaskDTO(gender, total_data);

        // 전체 성공
        RestTemplate restTemplate = new RestTemplate();
        ObjectMapper mapper = new ObjectMapper();

        //헤더를 JSON으로 설정함
        HttpHeaders headers = new HttpHeaders();

        //파라미터로 들어온 dto를 JSON 객체로 변환
        headers.setContentType(MediaType.APPLICATION_JSON);

        String param = mapper.writeValueAsString(result);

        HttpEntity<String> entity = new HttpEntity<String>(param , headers);

        //실제 Flask 서버랑 연결하기 위한 URL
        String url = "http://127.0.0.1:5000/" + gender.toLowerCase();

        //Flask 서버로 데이터를 전송하고 받은 응답 값을 return
        return restTemplate.postForObject(url, entity, List.class); //이거는 return으로 내보낼 PRID 값들이라 LIst가 맞음
    }




}
