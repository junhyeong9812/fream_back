package com.kream.root.admin.controller;


import com.kream.root.admin.domain.UserAccessLog;
import com.kream.root.admin.domain.UserAccessLogDTO.GraphDataDTO;
import com.kream.root.admin.service.userAccess.UserAccessLogService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import lombok.extern.log4j.Log4j2;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping("/Access")
public class AccessController {

    @Autowired
    private UserAccessLogService userAccessLogService;

    @PostMapping("/logUserAccess")
    public void logUserAccess(HttpServletRequest request, @RequestBody UserAccessLog clientLog) {
        String refererUrl = request.getHeader("Referer");
        String userAgent = request.getHeader("User-Agent");

        userAccessLogService.logUserAccess(clientLog, refererUrl, userAgent);
    }
    @GetMapping("/os")
    public List<GraphDataDTO> getOsGraphData() {
        return userAccessLogService.getGraphDataByOs();
    }

    @GetMapping("/browser")
    public List<GraphDataDTO> getBrowserGraphData() {
        return userAccessLogService.getGraphDataByBrowser();
    }

    @GetMapping("/deviceType")
    public List<GraphDataDTO> getDeviceTypeGraphData() {
        return userAccessLogService.getGraphDataByDeviceType();
    }

    @GetMapping("/referer")
    public List<GraphDataDTO> getRefererGraphData() {
        return userAccessLogService.getGraphDataByRefererUrl();
    }

    // 사용자와 상품 간의 상호작용을 저장하는 엔드포인트 추가
    @PostMapping("/logUserInteraction")
    public void logUserInteraction(@RequestParam String userId, @RequestParam Long productId,HttpServletRequest request) {
        getRecentProductsFromCookies(request);
        userAccessLogService.saveInteraction(userId, productId);
    }

    private List<Long> getRecentProductsFromCookies(HttpServletRequest request) {
        String cookieName = "recentProducts";
        Cookie[] cookies = request.getCookies();


        if (cookies == null) {
//            log.info("cookies : " + cookies);
            System.out.println("cookies = " + cookies);
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
//                log.info("cookieValue : {}", cookieValue);
                System.out.println("decodedValue = " + decodedValue);
                return Arrays.stream(decodedValue.split(","))
                        .map(Long::valueOf)
                        .collect(Collectors.toList());
            } catch (Exception e) {
//                log.error("Error decoding cookie value", e);
                System.out.println("e = " + e);
            }
        }
        return new ArrayList<>();
    }

}
