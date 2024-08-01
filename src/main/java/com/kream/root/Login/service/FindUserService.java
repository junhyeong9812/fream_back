package com.kream.root.Login.service;

import com.kream.root.Login.model.Update_Pw;

public interface FindUserService {
    public String findEmail(String PhonNumber);
    public boolean findUserPw(String UserId,String PhonNumber);
    public boolean UpdatePassword(Update_Pw updatePw);
}
