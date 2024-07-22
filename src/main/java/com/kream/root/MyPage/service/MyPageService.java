package com.kream.root.MyPage.service;

import com.kream.root.Login.model.UserListDTO;

import com.kream.root.MyPage.dto.addressDTO;
import com.kream.root.entity.AddressBook;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface MyPageService { // 각각 기능이 저장될 때마다 다시 리로드 되므로

    //프로필 관리
    public Optional<UserListDTO> changeUserName(String userid, String userName);
    public Optional<UserListDTO> changeImg(String userid, MultipartFile img);
    public Optional<UserListDTO> changeProfileName(String userid, String ProfileName);
    public Optional<UserListDTO> changeIntroduce(String userid, String introduce);
    public Optional<UserListDTO> addBlockProfile(String userid, List<String> BlockProfile);

    //로그인 정보
    public Optional<UserListDTO> changeEmail(String userid, String email);
    public Optional<UserListDTO> changePassword(String userid, String oldPassword, String newPassword);
    public Optional<UserListDTO> changePhone(String userid, String phone);
    public Optional<UserListDTO> changeUser_size(String userid, String user_size);
    public Optional<UserListDTO> receiveEmail(String userid, String receive_email);
    public Optional<UserListDTO> receiveMessage(String userid, String receive_message);

    //주소록
    public List<AddressBook> getAddressBookList(int ulid);
    public List<AddressBook> setAddressBook(addressDTO addr);
    public void deleteAddressBook(addressDTO addr);
    public List<AddressBook> modifyAddressBook(addressDTO addr);
}
