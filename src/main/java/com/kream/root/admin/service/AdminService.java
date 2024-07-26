package com.kream.root.admin.service;


import com.kream.root.Login.model.UserListDTO;
import com.kream.root.admin.domain.Admin;
import com.kream.root.admin.repository.AdminRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminService {
    @Autowired
    private AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder; // PasswordEncoder로 정의

    public List<Admin> getAllAdmins() {
        return adminRepository.findAll();
    }


    @Transactional//이렇게 따로 설정하면 우선권을 가져서 디폴트값 펄스가 존재
    public Long join(Admin Admin){
        //같은 이름의 중복 회원 확인
        validateDuplicateAdmin(Admin);
        // 비밀번호 암호화
        Admin.setPassword(passwordEncoder.encode(Admin.getPassword()));
        //회원 정보 저장
        adminRepository.save(Admin);

        return Admin.getId();
    }
    private void validateDuplicateAdmin(Admin admin) {
        //중복 시 EXCEPTIO
        List<Admin> findAdmins = adminRepository.findByName(admin.getName());
        //동일한 이름을 가진 맴버 목록을가져온다.
        if(!findAdmins.isEmpty()){
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }//동일한 이름을 가진 맴버가 존재할 경우 throw를 통해 익셉션을 생성하고
        //새로운 익셉션을 생성한다.

    }


    public List<Admin> findAdmins(){
        return adminRepository.findAll();
    }
    //회원 단일 조회
//    @Transactional(readOnly = true)
    public Admin findOne(Long memberId){
        return adminRepository.findOne(memberId);

    }
    //정보 업데이트
    @Transactional
    public Admin updateAdmin(Long id, Admin admin) {
        Admin existingAdmin = adminRepository.findOne(id);
        if (existingAdmin == null) {
            throw new IllegalStateException("해당 회원이 존재하지 않습니다.");
        }
        existingAdmin.setUsername(admin.getUsername());
        existingAdmin.setPassword(admin.getPassword());
        existingAdmin.setName(admin.getName());
        existingAdmin.setAge(admin.getAge());
        existingAdmin.setPhoneNumber(admin.getPhoneNumber());
        existingAdmin.setEmail(admin.getEmail());
        existingAdmin.setAddress(admin.getAddress());
        existingAdmin.setAccessLevel(admin.getAccessLevel());
        existingAdmin.setRole(admin.getRole());
        existingAdmin.setStatus(admin.getStatus());
        existingAdmin.setProfilePicture(admin.getProfilePicture());
        existingAdmin.setDepartment(admin.getDepartment());
        existingAdmin.setNotes(admin.getNotes());
        return adminRepository.update(existingAdmin);
    }
}
