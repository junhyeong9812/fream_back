package com.kream.root.MyPage.controller;

import com.kream.root.Login.model.UserListDTO;
import com.kream.root.Login.repository.UserListRepository;
import jakarta.servlet.http.Cookie;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class MainMyPageController {

    @Autowired
    UserListRepository userListRepository;
//    @GetMapping("/my/test")
//    public String test(){
//        return "index";
//    }

    @GetMapping("MyPage")
    public Optional<UserListDTO> mainMyPage(@CookieValue(value = "loginCookie") Cookie cookie){
        String userid = cookie.getValue();

        return userListRepository.findByUserId(userid);

    }
}
