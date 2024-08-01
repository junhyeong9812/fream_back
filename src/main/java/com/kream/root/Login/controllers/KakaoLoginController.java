package com.kream.root.Login.controllers;

import com.kream.root.Login.jwt.JwtTokenProvider;
import com.kream.root.Login.model.KakaoUserDTO;
import com.kream.root.Login.model.SignInResultDTO;
import com.kream.root.Login.model.UserListDTO;
import com.kream.root.Login.repository.UserListRepository;
import com.kream.root.Login.service.KakaoService;
import com.kream.root.Login.service.SignService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.net.http.HttpResponse;

@CrossOrigin
@RestController
@Log4j2
public class KakaoLoginController {

    @Autowired
    private KakaoService kakaoService;

    @Autowired
    private UserListRepository userListRepository;

    @Autowired
    private SignService signService;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @GetMapping("/kakaoLogin")
    public ModelAndView kakaoLogin(@RequestParam("code") String code, HttpSession session, HttpServletResponse response) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        try {
            //1.인가 코드 받기 (@RequestParam String code)
            System.out.println(code);

            //2.Access Token 받아오기
            String accessToken = kakaoService.getKakaoAccessToken(code);

            //3.사용자 정보 받아오기
            KakaoUserDTO getUserInfo = kakaoService.getUserInfo(accessToken);

            String email = getUserInfo.getKakaoEmail();
            String nickname = getUserInfo.getKakaoNickname();

            System.out.println("email = " + email);
            System.out.println("nickname = " + nickname);
            System.out.println("accessToken = " + accessToken);

            String jwtToken = null;
            String userId = null;
            if (!accessToken.isEmpty()){
                UserListDTO dto = kakaoService.setKakaoUserInfo(getUserInfo);
                jwtToken = KakaoLoginJWT(dto, response);

                userId = dto.getUserId();;
            }
            log.info("userId : " + userId);
            // 리다이렉트 설정 -> 토큰 값과 userId를 전달, 프론트에서 cookie값 생성
            String redirectUrl = "http://localhost:3000/yourTargetPage?jwtToken=" + jwtToken
                    + "&loginCookie=" + userId;
            modelAndView.setViewName("redirect:" + redirectUrl);
            return modelAndView;

        } catch (Exception e) {
            String redirectUrl = "/login";
            modelAndView.setViewName("redirect:" + redirectUrl);
            return modelAndView;

        }
    }

    public String KakaoLoginJWT(UserListDTO dto, HttpServletResponse response){
        log.info("[signIn] Attempting to log in. with id : {}", dto.getUserId());
        SignInResultDTO signInResultDTO = kakaoService.signIn( dto.getUserId());

        if (signInResultDTO.getCode() == 1) {
            log.info("[signIn] Successfully logged in. id : {}, token : {}", dto.getUserId(), signInResultDTO.getToken());

            // Get JWT token from signInResultDTO
            String jwtToken = signInResultDTO.getToken();




            if (jwtTokenProvider.validateToken(jwtToken)) {

                Authentication authentication = jwtTokenProvider.getAuthentication(jwtToken);

                System.out.println("jwtToken:"+jwtToken);
                System.out.println("authentication:"+authentication);

                SecurityContextHolder.getContext().setAuthentication(authentication);

                return jwtToken;
            } else {
                log.info("JWT token validation failed for user id: {}", dto.getUserId());
                return null;
            }
        } else {
            log.info("Login failed for user id: {}",dto.getUserId());

            return null;
        }
    }
}