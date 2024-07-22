package com.kream.root.MyPage.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kream.root.Login.model.UserListDTO;
import com.kream.root.Login.repository.UserListRepository;

import com.kream.root.MyPage.dto.addressDTO;
import com.kream.root.MyPage.mapping.ProfileNameMapping;

import com.kream.root.entity.AddressBook;
import com.kream.root.order.repository.AddressBookRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Log4j2
public class MyPageServiceImpl implements MyPageService{

    @Autowired
    UserListRepository userListRepository;
    @Autowired
    MyPageImgService mi;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    AddressBookRepository addressBookRepository;

    //프로필 관리
    @Override
    public Optional<UserListDTO> changeProfileName(String userid, String ProfileName) {
        List<String> ProfileNList = new ArrayList<>();

        List<ProfileNameMapping> ProfileNameList =  userListRepository.findAllByProfileNameIsNotNull();
        Optional<UserListDTO> userList = userListRepository.findByUserId(userid);

        ProfileNameList.forEach(pName -> ProfileNList.add(pName.getProfileName()) );

//        String oldPName = ProfileNameList;
        if (!ProfileNList.contains(ProfileName)){ //새로 변경하는 이름이 기존 ProfileName에 없을 경우 추가해 주세요
            userList.get().setProfileName(ProfileName);

            log.info(userList.get().getProfileName());
            userListRepository.save(userList.get());
            return userList;
        }
        else {
            return null;
        }
    }

    @Override
    public Optional<UserListDTO> changeUserName(String userid, String userName) {
        Optional<UserListDTO> userList = userListRepository.findByUserId(userid);
        userList.get().setUserName(userName);
        userListRepository.save(userList.get());
        log.info(userList.get());
        return userList;
    }

    @Override
    public Optional<UserListDTO> changeImg(String userid, MultipartFile img) {
        String uploadPath = "src/main/resources/static/upload/ProfileImg";
        String dbSaveImgName = null;
        try {
            if (img != null){
                dbSaveImgName = mi.SaveImg(userid, img, uploadPath);
            }
            else {
                mi.deleteUserImg(uploadPath, userid);
            }

        } catch (Exception e){
            e.printStackTrace();
        }
        Optional<UserListDTO> userList = userListRepository.findByUserId(userid);
        userList.get().setProfileUrl(dbSaveImgName);
        userListRepository.save(userList.get());

        return userList;
    }

    @Override
    public Optional<UserListDTO> changeIntroduce(String userid, String introduce) {
        Optional<UserListDTO> userList = userListRepository.findByUserId(userid);
        userList.get().setUserBio(introduce);
        userListRepository.save(userList.get());

        return userList;
    }

    @Override
    public Optional<UserListDTO> addBlockProfile(String userid, List<String> BlockProfile) {
        //Block profile String[] 으로 받아서 UserListDto 형태로 풀어서 데이터베이스에 저장
        Optional<UserListDTO> userList = userListRepository.findByUserId(userid); // 저장할 기준 데이터베이스
        //언니의 구현 방향에 따라 변경할 예정
//        try {

//            List<String> BlockList = null;

//            if (userList.get().getBlockedProfiles() != null) {
//                log.info("여기");
//                String bList = userList.get().getBlockedProfiles();
//                ObjectMapper objectMapper = new ObjectMapper();
//                bList = bList.replace("'", "\"");
//                BlockList = objectMapper.readValue(bList.toString(), new TypeReference<List<String>>() {
//                });
//
//                List<String> finalBlockList = BlockList;
//                BlockProfile.forEach(prof -> {
//                    finalBlockList.add(prof);
//                });
//
//
//            } else {
//                BlockList = new ArrayList<>();
//                List<String> finalBlockList = BlockList;
//                BlockProfile.forEach(prof -> {
//                    finalBlockList.add(prof);
//                });
//            }
            // blocklist를 userList에 저장
            // 현재 방식 - 프론트에서 프로필이름 리스트를 추가 삭제 해서 전달 -> 그대로 받아서 저장
            List<String> canBlockList = new ArrayList<>();
            log.info("BlockList : {}", BlockProfile);
            List<String> profileList = userListRepository.findAllByProfileNameIsNotNull().stream().map(ProfileNameMapping::getProfileName).collect(Collectors.toList()); //
            log.info(profileList);
            BlockProfile.forEach(prof -> {
                if (profileList.contains(prof)){
                    log.info(prof);
                    canBlockList.add(prof);
                }
            });
        log.info("canBlockList : {}", canBlockList);
            userList.get().setBlockedProfiles(canBlockList.toString()); // 리스트를 String 화 해서 불러오기
            userListRepository.save(userList.get());
            log.info("userList : {}", userList);
            return userList;
//        }catch (Exception e){
//            e.printStackTrace();
//            return userList;
//        }

    }

    //로그인 정보
    @Override
    public Optional<UserListDTO> changeEmail(String userid, String email) {
        Optional<UserListDTO> userList = userListRepository.findByUserId(userid);
        userList.get().setEmail(email);
        userListRepository.save(userList.get());

        return userList;
    }

    @Override
    public Optional<UserListDTO> changePassword(String userid, String oldPassword, String newPassword) {
        Optional<UserListDTO> userList = userListRepository.findByUserId(userid);
        String pw = userList.get().getPassword();
        if (passwordEncoder.matches(oldPassword, pw)) { //raw, encoded
            userList.get().setUserPw(passwordEncoder.encode(newPassword));
            userListRepository.save(userList.get());
            return userList;
        } else {
            return null; // 현재 비밀번호가 틀림
        }

    }

    @Override
    public Optional<UserListDTO> changePhone(String userid, String phone) {
        Optional<UserListDTO> userList = userListRepository.findByUserId(userid);
        userList.get().setPhone(phone);
        userListRepository.save(userList.get());

        return userList;
    }

    @Override
    public Optional<UserListDTO> changeUser_size(String userid, String user_size) {
        Optional<UserListDTO> userList = userListRepository.findByUserId(userid);
        userList.get().setUserSize(user_size);
        userListRepository.save(userList.get());

        return userList;
    }

    @Override
    public Optional<UserListDTO> receiveEmail(String userid, String receive_email) {
        Optional<UserListDTO> userList = userListRepository.findByUserId(userid);

        String onCheck = "0";
        if (receive_email.equals("1")){
            onCheck = "1";
        }
        userList.get().setReceiveEmail(onCheck);
        userListRepository.save(userList.get());

        return userList;
    }

    @Override
    public Optional<UserListDTO> receiveMessage(String userid, String receive_message) {
        Optional<UserListDTO> userList = userListRepository.findByUserId(userid);

        String onCheck = "0";
        if (receive_message.equals("1")){
            onCheck = "1";
        }
        userList.get().setReceiveMessage(onCheck);
        userListRepository.save(userList.get());

        return userList;
    }

    @Override
    public List<AddressBook> getAddressBookList(int ulid) {

        return addressBookRepository.listAddressBook(ulid);
    }

    @Override
    public List<AddressBook> setAddressBook(addressDTO addr) {

        int ulid = userListRepository.findByUserId(addr.getUserId()).get().getUlid();

        UserListDTO.Builder builder = new UserListDTO.Builder();

        UserListDTO dto = builder.ulid(ulid).build();
        log.info(dto);

        AddressBook addrBook = AddressBook.builder()
                .user(dto)
                .city(addr.getCity())
                .postalCode(addr.getPostalCode())
                .phone(addr.getPhone())
                .name(addr.getName())
                .street(addr.getStreet())
                .isDefault(addr.getIsDefault())
                .build();

        addressBookRepository.save(addrBook);

        return addressBookRepository.listAddressBook(ulid);
    }

    @Override
    public void deleteAddressBook(addressDTO addr) {

        Long addrId = addr.getAddress_id();

        addressBookRepository.delete(addressBookRepository.findById(addrId).orElseGet(()-> {
            throw new IllegalArgumentException("없는 주소록 입니다");
        }));

    }

    @Override
    public List<AddressBook> modifyAddressBook(addressDTO addr) {

        int ulid = userListRepository.findByUserId(addr.getUserId()).get().getUlid();
        Long addrId = addr.getAddress_id();

        UserListDTO.Builder builder = new UserListDTO.Builder();
        UserListDTO dto = builder.ulid(ulid).build();

        AddressBook addrBook = AddressBook.builder()
                .user(dto)
                .city(addr.getCity())
                .postalCode(addr.getPostalCode())
                .phone(addr.getPhone())
                .name(addr.getName())
                .street(addr.getStreet())
                .isDefault(addr.getIsDefault())
                .build();

        addressBookRepository.save(addrBook);

        return addressBookRepository.listAddressBook(ulid);
    }
}
