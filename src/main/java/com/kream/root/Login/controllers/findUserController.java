package com.kream.root.Login.controllers;

import com.kream.root.Login.model.FindEmailDTO;
import com.kream.root.Login.model.Find_UserPwDTO;
import com.kream.root.Login.model.Update_Pw;
import com.kream.root.Login.service.FindUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/login")
public class findUserController {

    @Autowired
    public FindUserService findUserService;

    @PostMapping("/find_email")
    public ResponseEntity<String> findEmail(@RequestBody FindEmailDTO findEmailDTO){
        try {
            String email =  findUserService.findEmail(findEmailDTO.getPhonNum());
            System.out.println("email = " + email);
            return ResponseEntity.ok(email);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PostMapping("/find_UserPw")
    public ResponseEntity<String> findUserPw(@RequestBody Find_UserPwDTO findUserPwDTO){
        boolean userExists = findUserService.findUserPw(findUserPwDTO.getEmail(), findUserPwDTO.getPhonNum());
        if (userExists) {
            return ResponseEntity.ok("User found");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
    }

    @PostMapping("/update_password")
    public ResponseEntity<String> findPassword(@RequestBody Update_Pw updatePw){
        try {
            boolean isUpdated = findUserService.UpdatePassword(updatePw);
            if (isUpdated) {
                return ResponseEntity.ok("Password updated successfully");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
            }
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }

    }


}
