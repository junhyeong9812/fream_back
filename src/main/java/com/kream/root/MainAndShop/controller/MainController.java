package com.kream.root.MainAndShop.controller;

import com.kream.root.Login.model.UserListDTO;
import com.kream.root.Login.repository.UserListRepository;
import com.kream.root.MainAndShop.dto.OneProductDTO;
import com.kream.root.MainAndShop.service.mainService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.Cookie;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

@Tag(name = "Main", description = "MainPage API 입니다.")
@RestController
@Log4j2
@RequiredArgsConstructor //의존성 주입을 위함
public class MainController {

    private final mainService ms;
    @Autowired
    UserListRepository userListRepository;

    @CrossOrigin(origins = "http://localhost:3000")
    @Operation(summary = "Main Data", description = "메인 데이터 전송")
    @ApiResponse(responseCode = "200")
    @GetMapping("/home")
    public List<OneProductDTO> openMain(@CookieValue(value = "loginCookie", required = false) Cookie loginCookie){
        // 보내야 할 데이터 목록
        //1. 브랜드명
        //2. 추천 상품
        //3. release 상품

        //추천 상품 보내는 쿼리와 연결
        // 예시 -- 나중에는 추천 데이터로 채움
        List<String> pridList = new ArrayList<>();

        //쿠키값에서 성별, 나이 가져오기
        int age = 20;
        String gender = "MAN";
        if (loginCookie != null){
            Optional<UserListDTO> user = userListRepository.findByUserId(loginCookie.getValue());
            age = (user.get().getAge() / 10) * 10;
            gender = user.get().getGender();
        }

        try{
            //추천 상품 보내는 쿼리와 연결
            //기본값은 남성, 20대, 로그인 시 해당 성별과 나잇대가 request로 들어와야 함
            pridList = ms.getRecommendList(age, gender);

        } catch (Exception e){
            e.printStackTrace();
        }
        // 산출되는 상품 리스트
        List<Long> rcmPList = pridList.stream().map(Long::parseLong)
                .collect(Collectors.toList());
        log.info("추천 리스트 : "+ rcmPList);
//        List<Long> pridList = LongStream.rangeClosed(1L, 10L).boxed().collect(Collectors.toList());

        List<OneProductDTO> productList = ms.topProductList(rcmPList);
//        System.out.println("home데이터");
        productList.forEach(d -> log.info(d));
        return productList;
    }

}
