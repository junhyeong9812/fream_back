package com.kream.root.Login.jwt;


import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;


public class JwtAuthenticationFilter extends OncePerRequestFilter {

 private final Logger LOGGER = LoggerFactory.getLogger(JwtAuthenticationFilter.class);
 private final JwtTokenProvider jwtTokenProvider;

 public JwtAuthenticationFilter(JwtTokenProvider jwtTokenProvider) {
     this.jwtTokenProvider = jwtTokenProvider;
 }

 @Override
 protected void doFilterInternal(HttpServletRequest servletRequest,  HttpServletResponse servletResponse, FilterChain filterChain) throws ServletException, IOException {
	 
	 
//	 String token= servletRequest.getParameter("token");
     String token = resolveToken(servletRequest);
	 LOGGER.info("[doFilterInternal] token : {}", token  );

	

	 
     //String token = jwtTokenProvider.resolveToken(servletRequest); 헤더로 넘겨주게 코드 바꾸면 이거써야함  근데 헤더로 구현 못할듯
     LOGGER.info("[doFilterInternal] token 값 추출 완료. token : {}", token);
     
     LOGGER.info("[doFilterInternal] token 값 유효성 체크 시작");
     if (token != null && jwtTokenProvider.validateToken(token)) {
         Authentication authentication = jwtTokenProvider.getAuthentication(token);
         System.out.println("autne:"+authentication);
         SecurityContextHolder.getContext().setAuthentication(authentication);
         LOGGER.info("[doFilterInternal] token 값 유효성 체크 완료:"+SecurityContextHolder.getContext());
     }
     servletRequest.setAttribute("token", token); // need this code in order to NormalAuthController test can obtain the token data
     filterChain.doFilter(servletRequest, servletResponse);
 }
    private String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }
}