//package com.kream.root.main;
//
//import com.kream.root.Detail.service.ProductBigDataService;
//import com.kream.root.Login.service.SignServiceImpl;
//import lombok.extern.log4j.Log4j2;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//@SpringBootTest
//@Log4j2
//public class userBIgDataTest {
//
//    @Autowired
//    ProductBigDataService productBigDataService;
//
//    @Test
//    public void testClickUp(){
////        String userid = "aaa";
////        Long prId = 10L;
////
////        Assertions.assertNotNull(productBigDataService.ClickUp(userid, prId));
//    }
//
//    @Autowired
//    SignServiceImpl signService;
//
//    @Test
//    public void generatedUserData(){
////        for (int i = 60; i < 1060 ; i++){
////            int age = (int)(i / 10) < 15 ? (int)(i / 10) + 20 : (int)(i / 10) ;
////            if ((age >= 40) && age % 2 == 0){
////                    age = age - 20;
////            }
////            if (age >= 55){
////                age = (int)(age / 5) < 15 ? (int)(age / 5) + 10 : (int)(age / 5) ;
////                if ((age < 20) && (age % 2 == 1)){
////                    age = age + 15;
////                }
////            }
////            String phone = i < 100 ? "010-0000-00"+i : "010-0000-0"+i;
////            String gender = null;
////
////            if (i % 3 == 0){
////                gender = "MAN";
////            } else if (i % 3 == 1){
////                gender = "MAN";
////            } else {
////                gender = "WOMAN";
////            }
////
////            signService.signUp(
////                    "user"+i,
////                    "user"+i,
////                    "user"+i,
////                    "aaa",
////                    "user"+i+"@kream.com",
////                    phone,
////                    age,
////                    gender
////            );
////
//////            UserListDTO dto = builder
//////                    .ulid(i)
//////                    .userId("user"+i)
//////                    .userPw("user"+i)
//////                    .userName("user"+i)
//////                    .age(age)
//////                    .phone(i < 100 ? "010-0000-00"+i : "010-0000-0"+i)
//////                    .email("user"+i+"@kream.com")
//////                    .gender((i % 2 == 0) || (i > 200) ? "MAN" : "WOMAN")
//////                    .build();
//////
//////            dtoList.add(dto);
////        }
//    }
//}
