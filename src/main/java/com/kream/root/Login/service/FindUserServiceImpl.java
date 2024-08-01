package com.kream.root.Login.service;

import com.kream.root.Login.model.Update_Pw;
import com.kream.root.Login.model.UserListDTO;
import com.kream.root.Login.repository.UserListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Service
public class FindUserServiceImpl implements FindUserService {
    @Autowired
    private UserListRepository userListRepository;
//        public FindUserServiceImpl(UserListRepository userListRepository){
//            this.userListRepository=userListRepository;
//        }
    @Autowired
    PasswordEncoder passwordEncoder;

    public String findEmail(String phoneNumber){
        return userListRepository.findByPhone(phoneNumber)
                .map(UserListDTO::getEmail)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }


    public boolean findUserPw(String userId,String phoneNumber){
        return userListRepository.findByUserIdAndPhone(userId, phoneNumber).isPresent();
    }


    public boolean UpdatePassword(Update_Pw updatePw){
        UserListDTO user = userListRepository.findByPhone(updatePw.getPhonNum())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!user.getEmail().equals(updatePw.getEmail())) {
            throw new RuntimeException("Email does not match");
        }
//        passwordEncoder.encode(updatePw.getTemporaryPassword())
//        int rowsAffected = userListRepository.updatePassword(user.getUserId(), updatePw.getTemporaryPassword());
        int rowsAffected = userListRepository.updatePassword(user.getUserId(), passwordEncoder.encode(updatePw.getTemporaryPassword()));
        return rowsAffected > 0;
    }
//    Optional<UserListDTO> findByPhone(String phone);
//    Optional<UserListDTO> findByUserIdAndPhone(String userId, String phone);
//    @Modifying
//    @Transactional
//    @Query("UPDATE UserListDTO u SET u.userPw = :password WHERE u.userId = :userId")
//    void updatePassword(@Param("userId") String userId, @Param("password") String password);



}

