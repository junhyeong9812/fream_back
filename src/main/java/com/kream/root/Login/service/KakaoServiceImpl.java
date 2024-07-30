package com.kream.root.Login.service;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.kream.root.Login.Response.CommonResponse;
import com.kream.root.Login.controllers.AuthRestController;
import com.kream.root.Login.jwt.JwtTokenProvider;
import com.kream.root.Login.model.*;
import com.kream.root.Login.repository.UserListRepository;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

@Service
@Log4j2
public class KakaoServiceImpl implements KakaoService{

    @Autowired
    UserListRepository userListRepository;

    @Autowired
    AuthRestController authRestController;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public UserListDTO setKakaoUserInfo(KakaoUserDTO kakaoUser) {


        if (userListRepository.findByUserId(kakaoUser.getKakaoEmail().split("@")[0]).isEmpty()) {
            UserListDTO kakaoUserInfo = new UserListDTO.Builder()
                    .userId(kakaoUser.getKakaoEmail().split("@")[0])
                    .userPw(passwordEncoder.encode("password1111"))
                    .userName(kakaoUser.getKakaoNickname())
                    .gender("MAN")
                    .age(20)
                    .email(kakaoUser.getKakaoEmail())
                    .phone("010-1111-1111")
                    .profileName(AuthRestController.generateRandomString(10))
                    .build();

            userListRepository.save(kakaoUserInfo);
            return kakaoUserInfo;
        }
        UserListDTO user = userListRepository.findByUserId(kakaoUser.getKakaoEmail().split("@")[0]).get();
//        TempUserDTO dto =  new TempUserDTO(user.getUserId(), user.getUserPw());
//        authRestController.loginCheck(dto, response);
        return user;
    }

    @Override
    public String getKakaoAccessToken(String authorization_code) throws Exception {
        System.out.println("---토큰발급---");
        String accessToken  = "";
        String refreshToken  = "";

        //카카오 토큰 발급 요청을 위한 URL
        String reqURL = "https://kauth.kakao.com/oauth/token";

        try {
            //URL객체 생성
            URL url = new URL(reqURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            log.info(conn);

            conn.setRequestMethod("POST");
            conn.setDoOutput(true);

            //POST 요청에 필요로 요구하는 파라미터 스트림을 통해 전송
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
            StringBuilder sb = new StringBuilder();


            sb.append("grant_type=authorization_code");
            sb.append("&client_id=2c38a672bc98d7bf79b19bbcaeb91eb6");
            sb.append("&redirect_uri=http://192.168.0.101:3001/kakaoLogin");
            sb.append("&code=" + authorization_code);

            bw.write(sb.toString());
            bw.flush();

            //응답확인 200이면 성공
            int responseCode = conn.getResponseCode();
            System.out.println("responseCode : " + responseCode);

            //요청을 통해 얻은 JSON 타입의 Response 메시지 읽어오기
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder resultBuilder = new StringBuilder();
            String line;

            while ((line = br.readLine()) != null) {
                resultBuilder.append(line);
            }
            String result = resultBuilder.toString();
            System.out.println("response body : " + result);

            //Gson 객체를 사용하여 JSON 파싱
            Gson gson = new Gson();
            JsonObject jsonObject = gson.fromJson(result, JsonObject.class);

            accessToken = jsonObject.get("access_token").getAsString();
            refreshToken = jsonObject.get("refresh_token").getAsString();

            System.out.println("access_token : " + accessToken);
            System.out.println("refresh_token : " + refreshToken);

            br.close();
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return accessToken;
    }

    //회원정보 요청, 사용자 정보 보기
    public KakaoUserDTO getUserInfo(String access_Token) throws IOException {
        System.out.println("---사용자 정보 보기---");

        UserListDTO getUserInfo = new UserListDTO();

        //토큰을 이용하여 카카오에 회원정보 요청
        String reqURl = "https://kapi.kakao.com/v2/user/me";

        try {
            //URL 객체 생성
            URL url = new URL(reqURl);
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Authorization", "Bearer " + access_Token);
            conn.setRequestProperty("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

            //응답코드 확인
            int responseCode = conn.getResponseCode();
            System.out.println("responseCode: " + responseCode);

            //응답 메시지 읽어오기
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
            StringBuilder resultBuilder = new StringBuilder();
            String line;

            while ((line = br.readLine()) != null) {
                resultBuilder.append(line);
            }
            String result = resultBuilder.toString();
            System.out.println("response body: " + result);

            //Gson을 이용하여 JSON 파싱
            Gson gson = new Gson();
            JsonObject jsonObject = gson.fromJson(result, JsonObject.class);

            JsonObject properties = jsonObject.getAsJsonObject("properties");
            JsonObject kakao_account = jsonObject.getAsJsonObject("kakao_account");
            Long id = jsonObject.get("id").getAsLong();

            //데이터 추출
            String kakaoNickname = properties.get("nickname").getAsString();
            String kakaoEmail = kakao_account.get("email").getAsString();

            System.out.println("nickname: " + kakaoNickname);
            System.out.println("email: " + kakaoEmail);

            //KakaoUserDTO에 담기
            KakaoUserDTO kakaoUserDTO = new KakaoUserDTO();
            kakaoUserDTO.setKakaoEmail(kakaoEmail);
            kakaoUserDTO.setKakaoNickname(kakaoNickname);

            br.close();
            return kakaoUserDTO;

        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        }
    }

    // 결과 모델에 api 요청 성공 데이터를 세팅해주는 메소드
    private void setSuccessResult(SignUpResultDTO result) {
        result.setSuccess(true);
        result.setCode(CommonResponse.SUCCESS.getCode());
        result.setMsg(CommonResponse.SUCCESS.getMsg());
    }
    // 결과 모델에 api 요청 실패 데이터를 세팅해주는 메소드
    private void setFailResult(SignUpResultDTO result) {
        result.setSuccess(false);
        result.setCode(CommonResponse.FAIL.getCode());
        result.setMsg(CommonResponse.FAIL.getMsg());
    }

    @Autowired
    JwtTokenProvider jwtTokenProvider;

    @Transactional
    @Override
    public SignInResultDTO signIn(String userId) {
        log.info("로그인 진행중");
        UserListDTO userDTO = userListRepository.getByUserId(userId);

        SignInResultDTO signInResultDTO = new SignInResultDTO(); // Initialize the object here
        userListRepository.updateLastLoginTime(userId);
        String token = jwtTokenProvider.createToken(String.valueOf(userDTO.getUserId()), userDTO.getRoles());
        signInResultDTO = new SignInResultDTO.Builder()
                    .token(token)
                    .build();
            setSuccessResult(signInResultDTO);
            log.info("Generated token: {}", token);
            System.out.println("token:" + token);
            return signInResultDTO;
        }
}