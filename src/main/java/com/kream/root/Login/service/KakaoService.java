package com.kream.root.Login.service;

import com.kream.root.Login.model.KakaoUserDTO;
import com.kream.root.Login.model.SignInResultDTO;
import com.kream.root.Login.model.UserListDTO;

import java.io.IOException;

public interface KakaoService {

    public UserListDTO setKakaoUserInfo(KakaoUserDTO kakaoUser);

    public String getKakaoAccessToken(String code) throws Exception;

    public KakaoUserDTO getUserInfo(String accessToken) throws IOException;

    public SignInResultDTO signIn(String userId);
}
