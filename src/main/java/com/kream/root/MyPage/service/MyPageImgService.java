package com.kream.root.MyPage.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface MyPageImgService {

    String SaveImg(String userid, MultipartFile img, String uploadDir);
    void deleteUserImg (String uploadDir, String userid) throws IOException;
}
