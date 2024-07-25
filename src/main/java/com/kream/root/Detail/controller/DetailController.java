package com.kream.root.Detail.controller;

import com.kream.root.Detail.dto.OneProductDTO;
import com.kream.root.Detail.service.ProductBigDataService;
import com.kream.root.Detail.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

//@CrossOrigin
@Tag(name = "Datail", description = "Detail Page API")
@RestController
@RequestMapping("/products")
@Log4j2
@RequiredArgsConstructor
public class DetailController {
    private final ProductService ps;
    private final ProductBigDataService bds;

    @CrossOrigin(origins = "http://localhost:3000")
    @Operation(
            summary = "Detail Data", description = "상품 상세 데이터 전송"
    )
    @ApiResponse(responseCode = "200", description = "product 상세 정보 가져오기 성공")
    @GetMapping(value = "/{prId}")
    public ResponseEntity<List<OneProductDTO>> getProductDetail(@PathVariable("prId") long prId, HttpServletRequest request, HttpServletResponse response,
                                                                @CookieValue(value = "loginCookie", required = false) Cookie cookie) {
        //log.info("Fetching product detail for prId: {}", prId);

        try {
            List<OneProductDTO> productDTO = ps.getProductDetail(prId);
            if (cookie != null){
                bds.ClickUp(cookie.getValue(), prId);
            }

            if (productDTO.isEmpty()) {
                log.warn("Invalid product ID requested: {}", prId);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(productDTO);
            }

            //최근 본 상품 쿠키 업데이트
            updateRecentProductsCookie(prId, request, response);

            return ResponseEntity.ok(productDTO);
        } catch (Exception e) {
            log.error("Error fetching product detail for prId: {}", prId, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ArrayList<>());
        }
    }

    @Operation(
            summary = "Products by Brand", description = "동일한 브랜드의 다른 상품 데이터 전송"
    )
    @ApiResponse(responseCode = "200", description = "products by Brand 정보 가져오기 성공")
    @GetMapping(value = "/{prId}/brand")
    public ResponseEntity<List<OneProductDTO>> getProductsByBrand(@PathVariable("prId") Long prId) {
        //log.info("Fetching products with brand: {} excluding prId: {}", brand, prId);
        try {
            //들어온 prId를 통해 해당 제품의 브랜드를 추출하여 동일한 브랜드의 다른 제품들을 반환
            List<OneProductDTO> productsByBrand = ps.getProductsByBrand(prId);

            return ResponseEntity.ok(productsByBrand);
        } catch (Exception e) {
            log.error("Error fetching products by brand for prId: {}", prId, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ArrayList<>());
        }
    }

    @Operation(
            summary = "Products by Gender", description = "동일한 성별의 다른 상품 데이터 전송"
    )
    @ApiResponse(responseCode = "200", description = "products by gender 정보 가져오기 성공")
    @GetMapping(value = "/{prId}/gender")
    public ResponseEntity<List<OneProductDTO>> getProductsByGender(@PathVariable("prId") Long prId) {
        //log.info("Fetching products with gender: {} excluding prId: {}", gender, prId);
        try {
            //들어온 prId를 통해 해당 제품의 성별을 추출하여 동일한 성별의 다른 제품들을 반환
            List<OneProductDTO> productsByGender = ps.getProductsByGender(prId);

            return ResponseEntity.ok(productsByGender);
        } catch (Exception e) {
            log.error("Error fetching products by gender for prId: {}", prId, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ArrayList<>());
        }
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @Operation(
            summary = "Recent Views", description = "최근 본 상품 목록 전송"
    )
    @ApiResponse(responseCode = "200", description = "recent views 정보 가져오기 성공")
    @GetMapping(value = "/recent-views")
    public ResponseEntity<List<OneProductDTO>> getRecentViews(HttpServletRequest request) {
        try {
            List<Long> recentProductsIds = getRecentProductsFromCookies(request);
            //log.info("Fetching recent products: {}", recentProductsIds);

            List<OneProductDTO> recentViews = ps.getProductsByPrId(recentProductsIds);
            System.out.println("recent-views:"+recentViews);
            return ResponseEntity.ok(recentViews);
        } catch (Exception e) {
            log.error("Error fetching recent views", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ArrayList<>());
        }
    }

    public void updateRecentProductsCookie(Long prId, HttpServletRequest request, HttpServletResponse response) {
        String cookieName = "recentProducts";
        int maxProducts = 5;

        //현재 쿠키에서 최근 본 상품 ID 리스트 가져오기
        List<Long> recentProducts = getRecentProductsFromCookies(request);
        //log.info("recentProducts : " + recentProducts);

        //이미 있는 상품 ID인 경우 기존 ID 제거하고 맨 앞에 추가
        if (recentProducts.contains(prId)) {
            recentProducts.remove(prId);
        }
        recentProducts.add(0, prId);
        //log.info("recentProducts : " + recentProducts);

        //최대 개수 초과하는 경우 가장 오래된 상품 제거
        if (recentProducts.size() > maxProducts) {
            recentProducts = recentProducts.subList(0, maxProducts);
        }

        //쿠키에 최신 상품 ID 리스트 저장
        try {
            String cookieValue = URLEncoder.encode(String.join(",", recentProducts.stream().map(String::valueOf).toArray(String[]::new)), StandardCharsets.UTF_8.toString());
            Cookie cookie = new Cookie(cookieName, cookieValue);
            cookie.setPath("/");
            cookie.setMaxAge(60 * 60 * 24 * 7); //쿠키 유효기간 7일
            cookie.setSecure(true); //이 속성과
            cookie.setAttribute("SameSite", "None"); //이 속성 추가
            response.addCookie(cookie);
            //log.info("Updated recent products cookie: {}", cookie.getValue());
            log.info("Recent products list: {}", recentProducts); //로그로 최근 본 상품 데이터 리스트 출력
        } catch (Exception e) {
            log.error("Error encoding cookie value", e);
        }
    }

    private List<Long> getRecentProductsFromCookies(HttpServletRequest request) {
        String cookieName = "recentProducts";
        Cookie[] cookies = request.getCookies();

        if (cookies == null) {
            log.info("cookies : " + cookies);
            return new ArrayList<>(); //쿠키가 없는 경우 빈 리스트 반환
        }

        Optional<Cookie> recentProductsCookie = Arrays.stream(request.getCookies())
                .filter(cookie -> cookieName.equals(cookie.getName()))
                .findFirst();

        String cookieValue = recentProductsCookie.map(Cookie::getValue).orElse("");

        //쿠키 값 디코딩
        if (!cookieValue.isEmpty()) {
            try {
                String decodedValue = URLDecoder.decode(cookieValue, StandardCharsets.UTF_8.toString());
                log.info("cookieValue : {}", cookieValue);
                return Arrays.stream(decodedValue.split(","))
                        .map(Long::valueOf)
                        .collect(Collectors.toList());
            } catch (Exception e) {
                log.error("Error decoding cookie value", e);
            }
        }
        return new ArrayList<>();
    }
}