package com.kream.root.Login.service;


import com.kream.root.Login.model.SignInResultDTO;
import com.kream.root.Login.model.SignUpResultDTO;


public interface SignService {
	SignUpResultDTO signUp(String userId, String userPw, String userName, String role, String email, String phone, int age, String gender);
	SignInResultDTO signIn(String userId, String userPw);
	
	int redundancyCheck(String userId);
}
