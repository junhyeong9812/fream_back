package com.kream.root.Login.controllers;

import com.kream.root.Login.model.KakaoUserDTO;
import com.kream.root.Login.model.UserListDTO;
import com.kream.root.Login.repository.UserListRepository;
import com.kream.root.Login.service.KakaoService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/kakaoLogin")
public class KakaoLoginController {

    @Autowired
    private KakaoService kakaoService;

    @Autowired
    private UserListRepository userListRepository;

    @GetMapping
    public String kakaoLogin(@RequestParam("code") String code, HttpSession session) throws Exception {
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

            UserListDTO userListDTO = kakaoService.setKakaoUserInfo(getUserInfo);

            return "redirect:/";

        } catch (Exception e) {
            //예외 발생 시 적절한 오류 처리
            e.printStackTrace();
            return "error";
        }
    }
}