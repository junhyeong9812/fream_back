package com.kream.root.admin.service.AdminSign;

import com.kream.root.admin.domain.Admin;
import com.kream.root.admin.jwt.AdminJwtTokenProvider;
import com.kream.root.admin.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.List;

@Service
public class AdminSignService {
    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private AdminJwtTokenProvider adminJwtTokenProvider;
    @Autowired
    private TokenWhitelistService tokenWhitelistService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public String loginAndGenerateToken(String userId, String userPw) {
        Admin admin = adminRepository.findOne(Long.parseLong(userId));

        if (admin != null) {
            if (passwordEncoder.matches(userPw, admin.getPassword()) || userPw.equals(admin.getPassword())) {
                // 토큰 생성
                String token = adminJwtTokenProvider.createToken(userId, List.of("ROLE_ADMIN"));

                // 화이트리스트에 토큰 추가 (유효 기간 설정)
                tokenWhitelistService.addTokenToWhitelist(token, Duration.ofHours(1)); // 예: 1시간 유효
                // 로그 출력
//                logger.info("토큰 생성 완료: token = {}", token);
                System.out.println("관리자 토큰 생성 완료: token = " + token);

                return token;
            }
        }
        return null;
    }
    public Admin getAdminInfo(String token) {
        if (adminJwtTokenProvider.validateToken(token)) {
            String userId = adminJwtTokenProvider.getUsername(token);
            return adminRepository.findOne(Long.parseLong(userId));
        }
        return null;
    }
}
