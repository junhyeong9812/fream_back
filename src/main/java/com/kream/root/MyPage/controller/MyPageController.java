package com.kream.root.MyPage.controller;

import com.kream.root.Login.model.UserListDTO;
import com.kream.root.Login.repository.UserListRepository;
//import com.kream.root.MyPage.domain.AddressBook;
import com.kream.root.MyPage.dto.addressDTO;
//import com.kream.root.MyPage.repository.AddressBookRepository;
import com.kream.root.MyPage.mapping.ImgMapper;
import com.kream.root.MyPage.mapping.PasswordMapping;
import com.kream.root.MyPage.service.MyPageService;
import com.kream.root.entity.AddressBook;
import com.kream.root.order.repository.AddressBookRepository;
import io.swagger.models.Response;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.Cookie;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.authentication.PasswordEncoderParser;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Setter
@RestController
@RequestMapping("/my")
@Log4j2
@Tag(name = "MyPage", description = "MyPage API")
@NoArgsConstructor
@AllArgsConstructor
public class MyPageController {

    @Autowired
    UserListRepository userListRepository;

    @Autowired
    MyPageService ms ;

    @Autowired
    AddressBookRepository addressBookRepository;

    @CrossOrigin(origins = "http://localhost:3000")
    @Operation(summary = "MyPage Data", description = "마이페이지 데이터 전송")
    @ApiResponse(responseCode = "200")
    @PutMapping("/profile-edit") //프로필 관리
    public ResponseEntity<Optional<UserListDTO>>  mainMyPage(
            @CookieValue(value = "loginCookie") Cookie cookie,
                               @RequestParam(required = false, value = "img") MultipartFile img,
                               @RequestParam(required = false, value = "ProfileName") String ProfileName,
                               @RequestParam(required = false, value = "userName") String userName,
                               @RequestParam(required = false, value = "introduce") String introduce
//                                         @RequestParam(required = false) List<String> BlockProfile
                                         ){
        String userid =  cookie.getValue();


        Optional<UserListDTO> userData = null;

        if (img != null ) {
            userData = ms.changeImg(userid, img);
        }
        if (ProfileName != null ) {
            userData = ms.changeProfileName(userid, ProfileName);
        }
        if (userName != null ) {
            userData = ms.changeUserName(userid, userName);
            log.info(userData.get());
        }
        if (introduce != null ) {
            log.info("introduce : " + introduce);
            userData = ms.changeIntroduce(userid, introduce);
        }
//        if (BlockProfile != null ) { //이부분은 프론트와 상의 필요
//            userData = ms.addBlockProfile(userid, BlockProfile);
//        }

        if (userData == null){
            return new ResponseEntity<Optional<UserListDTO>>(userData, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Optional<UserListDTO>>(userData, HttpStatus.OK);
    }
    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/profile-edit")
    public Optional<UserListDTO> profileEditMyPage(@CookieValue(value = "loginCookie") Cookie cookie){
        String userid =  cookie.getValue();
        log.info(userid);

        Optional<UserListDTO> userData = userListRepository.findByUserId(userid);

        return userData;
    }

    @PutMapping("/profile-edit/img")
    public ResponseEntity<Optional<UserListDTO>>  profileImgChange(@CookieValue(value = "loginCookie") Cookie cookie,
//                                                                 @RequestPart(required = false, value = "file") MultipartFile formData)
                                                                   @RequestParam("file") MultipartFile file) {
        String userid =  cookie.getValue();
        Optional<UserListDTO> userData = null;
        System.out.println("파일데이터file = " + file);
        log.info("formData : " + file);
//        log.info("img : " + img);
//
//        if (!formData.isEmpty()){
//            userData = ms.changeImg(userid, formData.get("file"));
//        }
//        else {
            userData = ms.changeImg(userid, file);
//        }

        if (userData == null){
            return new ResponseEntity<Optional<UserListDTO>>(userData, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Optional<UserListDTO>>(userData, HttpStatus.OK);
    }

    @PutMapping("profile") //로그인정보
    public ResponseEntity<Optional<UserListDTO>> loginInfo(
            @CookieValue(value = "loginCookie") Cookie cookie,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String phone,
            @RequestParam(required = false, value = "uSize") String user_size,
            @RequestParam(required = false, value = "receiveEmail") String receive_email,
            @RequestParam(required = false, value = "receiveMessage") String receive_message
    ){
        //이메일주소
        //비밀번호
        //휴대폰번호
        //신발 사이즈
        //문자 정보 수신
        //이메일 정보 수신

        String userid =  cookie.getValue();
        log.info(userid);

        Optional<UserListDTO> userData = null;

        if (email != null ) {
            userData = ms.changeEmail(userid, email);
        }
        if (phone != null ) {
            userData = ms.changePhone(userid, phone);
            log.info(userData.get());
        }
        if (user_size != null ) {
            log.info("user_size : " + user_size);
            userData = ms.changeUser_size(userid, user_size);
        }
        if (receive_email != null ) {
            log.info("receive_email : " + receive_email);
            userData = ms.receiveEmail(userid, receive_email);
        }
        if (receive_message != null ) {
            log.info("receive_message : " + receive_message);
            userData = ms.receiveMessage(userid, receive_message);
        }

        if (userData == null){
            return new ResponseEntity<Optional<UserListDTO>>(userData, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<Optional<UserListDTO>>(userData, HttpStatus.OK);
    }

    @PutMapping("/profile/pwd") //로그인정보
    public ResponseEntity<Optional<UserListDTO>> pwdChange(
            @CookieValue(value = "loginCookie") Cookie cookie,
            @RequestBody PasswordMapping pwd
    ){

        String userid =  cookie.getValue();
        log.info("old : " + pwd.getOldPassword());
        log.info("new : " + pwd.getNewPassword());

        Optional<UserListDTO> userData = null;

        if (pwd.getOldPassword() != null && pwd.getNewPassword() != null ) {
            userData = ms.changePassword(userid, pwd.getOldPassword(), pwd.getNewPassword());

            // null : 현재 비밀번호가 틀림
            if (userData == null) {
                // 현재 비밀번호가 틀림
                return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
            } else {
                log.info("userData : {}", userData);
                return new ResponseEntity<>(userData, HttpStatus.OK);
            }
        }else {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }

    }
    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/profile")
    public Optional<UserListDTO> profileMyPage(@CookieValue(value = "loginCookie") Cookie cookie){
        String userid =  cookie.getValue();
        log.info(userid);

        Optional<UserListDTO> userData = userListRepository.findByUserId(userid);

        return userData;
    }
    @CrossOrigin(origins = "http://localhost:3000")
    @Operation(summary = "Address Get", description = "주소록")
    @ApiResponse(responseCode = "200")
    @GetMapping("/address")
    public List<AddressBook> getAddressMyPage(@CookieValue(value = "loginCookie") Cookie cookie){
        String userid =  cookie.getValue();

        int ulid = (userListRepository.findByUserId(userid)).orElseGet(()-> {
            throw new IllegalArgumentException("없는 정보 입니다.");
        }).getUlid();

        return addressBookRepository.findByUserUlid(ulid);
    }
    @CrossOrigin(origins = "http://localhost:3000")
    @Operation(summary = "Address Get", description = "주소록")
    @ApiResponse(responseCode = "200")
    @PostMapping("/address")
    public ResponseEntity<List<AddressBook>> postAddressMyPage(@CookieValue(value = "loginCookie") Cookie cookie,
                                               @RequestBody addressDTO dto){
        String userid =  cookie.getValue();

        dto.setUserId(userid);

        log.info("address dto : {}", dto);

        List<AddressBook> result =  ms.setAddressBook(dto);

        if (result == null){
            return new ResponseEntity<List<AddressBook>>(result, HttpStatus.BAD_REQUEST);
        }
        log.info("result : {}", result);
        return new ResponseEntity<List<AddressBook>>(result, HttpStatus.OK);
    }
    @CrossOrigin(origins = "http://localhost:3000")
    @Operation(summary = "Address Get", description = "주소록")
    @ApiResponse(responseCode = "200")
    @DeleteMapping("/address/{id}")
    public ResponseEntity deleteAddressMyPage(@CookieValue(value = "loginCookie") Cookie cookie,
                                              @PathVariable(name = "id") String addrId){
        String userid =  cookie.getValue();

        int ulid = (userListRepository.findByUserId(userid)).orElseGet(()-> {
            throw new IllegalArgumentException("없는 정보 입니다.");
        }).getUlid();

        Long address_id = Long.parseLong(addrId);
//        dto.setUserId(userid);
        try {
             ms.deleteAddressBook(ulid, address_id);

        } catch (IllegalArgumentException e ){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }
    @CrossOrigin(origins = "http://localhost:3000")
    @Operation(summary = "Address Get", description = "주소록")
    @ApiResponse(responseCode = "200")
    @PutMapping("/address")
    public ResponseEntity<List<AddressBook>> putAddressMyPage(@CookieValue(value = "loginCookie") Cookie cookie,
                                              @RequestBody addressDTO dto){


        String userid =  cookie.getValue();
        dto.setUserId(userid);
        List<AddressBook> result = ms.modifyAddressBook(dto);
        if (result == null){
            return new ResponseEntity<List<AddressBook>>(result, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<List<AddressBook>>(result, HttpStatus.OK);
    }
    @CrossOrigin(origins = "http://localhost:3000")
    @Operation(summary = "Address put isDefault", description = "주소록")
    @ApiResponse(responseCode = "200")
    @PutMapping("/address/is_default/{id}")
    public ResponseEntity<List<AddressBook>> putAddressMyPage(@CookieValue(value = "loginCookie") Cookie cookie,
                                              @PathVariable(name="id") String address_id){

        String userid =  cookie.getValue();
        int ulid = userListRepository.findByUserId(userid).get().getUlid();
        Long addrId = Long.parseLong(address_id);

        List<AddressBook> result = ms.modifyIsDefault(ulid, addrId);
        if (result == null){
            return new ResponseEntity<List<AddressBook>>(result, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<List<AddressBook>>(result, HttpStatus.OK);
    }
}
