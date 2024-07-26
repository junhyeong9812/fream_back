package com.kream.root.admin.jwt;
import com.kream.root.admin.service.AdminSign.AdminUserDetailsService;
import com.kream.root.admin.service.AdminSign.TokenWhitelistService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Date;
import java.util.List;

@Component
public class AdminJwtTokenProvider {

    private final Logger LOGGER = LoggerFactory.getLogger(AdminJwtTokenProvider.class);
    private final UserDetailsService adminUserDetailsService;
    private final TokenWhitelistService tokenWhitelistService;

    @Value("${springboot.jwt.secret}")
    private String secretKey;

    private final long tokenValidMillisecond = 1000L * 60 * 60;

    public AdminJwtTokenProvider(@Qualifier("adminUserDetailsService") UserDetailsService adminUserDetailsService, TokenWhitelistService tokenWhitelistService) {
        this.adminUserDetailsService = adminUserDetailsService;
        this.tokenWhitelistService = tokenWhitelistService;
    }

    @PostConstruct
    protected void init() {
        LOGGER.info("[init] AdminJwtTokenProvider 내 secretKey 초기화 시작");
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes(StandardCharsets.UTF_8));
        LOGGER.info("[init] AdminJwtTokenProvider 내 secretKey 초기화 완료");
    }

    private SecretKey getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(this.secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String createToken(String userUid, List<String> roles) {
        LOGGER.info("[createToken] Admin 토큰 생성 시작");
        Claims claims = Jwts.claims().setSubject(userUid);
        claims.put("roles", roles);

        Date now = new Date();
        String token = Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + tokenValidMillisecond))
                .signWith(getSigningKey())
                .compact();

        LOGGER.info("[createToken] Admin 토큰 생성 완료");
        return token;
    }

    public Authentication getAuthentication(String token) {
        LOGGER.info("[getAuthentication] Admin 토큰 인증 정보 조회 시작");
        System.out.println("[getAuthentication] Admin 토큰 인증 정보 조회 시작token = " + token);
        UserDetails userDetails = adminUserDetailsService.loadUserByUsername(this.getUsername(token));
        System.out.println("검사userDetails = " + userDetails);
        LOGGER.info("[getAuthentication] Admin 토큰 인증 정보 조회 완료, UserDetails UserName : {}",
                userDetails.getUsername());
        return new UsernamePasswordAuthenticationToken(userDetails, "",
                userDetails.getAuthorities());
    }

    public String getUsername(String token) {
        LOGGER.info("[getUsername] Admin 토큰 기반 회원 구별 정보 추출");
        String info = Jwts.parserBuilder().setSigningKey(getSigningKey()).build().parseClaimsJws(token).getBody().getSubject();
        LOGGER.info("[getUsername] Admin 토큰 기반 회원 구별 정보 추출 완료, info : {}", info);
        return info;
    }

    public String resolveToken(HttpServletRequest request) {
        LOGGER.info("[resolveToken] HTTP 헤더에서 Token 값 추출");
        String bearerToken = request.getHeader("Authorization");
        LOGGER.info("[resolveToken] HTTP 헤더에서 Token 값 추출:"+bearerToken);
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }

    public boolean validateToken(String token) {
        LOGGER.info("[validateToken] Admin 토큰 유효 체크 시작");

        try {
            if (tokenWhitelistService.isTokenInWhitelist(token)) {
                LOGGER.info("[validateToken] Admin 토큰이 화이트리스트에 있습니다.");
                Jws<Claims> claims = Jwts.parserBuilder()
                        .setSigningKey(getSigningKey())
                        .build()
                        .parseClaimsJws(token);

                LOGGER.info("[validateToken] Admin 토큰 유효 체크 완료");
                return !claims.getBody().getExpiration().before(new Date());
            } else {
                LOGGER.info("[validateToken] Admin 토큰이 화이트리스트에 없습니다.");
                return false;
            }
        } catch (Exception e) {
            LOGGER.info("[validateToken] Admin 토큰 유효 체크 예외 발생");
            return false;
        }
    }

    public Date getExpirationDate(String token) {
        LOGGER.info("[getExpirationDate] Admin 토큰 만료 날짜 조회 시작");
        Jws<Claims> claims = Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token);
        Date expiration = claims.getBody().getExpiration();
        LOGGER.info("[getExpirationDate] Admin 토큰 만료 날짜 조회 완료, expiration : {}", expiration);
        return expiration;
    }
}