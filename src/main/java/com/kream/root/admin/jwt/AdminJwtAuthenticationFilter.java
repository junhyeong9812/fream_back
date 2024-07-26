package com.kream.root.admin.jwt;

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

public class AdminJwtAuthenticationFilter extends OncePerRequestFilter {

    private final Logger LOGGER = LoggerFactory.getLogger(AdminJwtAuthenticationFilter.class);
    private final AdminJwtTokenProvider adminJwtTokenProvider;

    public AdminJwtAuthenticationFilter(AdminJwtTokenProvider adminJwtTokenProvider) {
        this.adminJwtTokenProvider = adminJwtTokenProvider;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = adminJwtTokenProvider.resolveToken(request);
        LOGGER.info("[doFilterInternal] Admin token : {}", token);

        if (token != null && adminJwtTokenProvider.validateToken(token)) {
            Authentication authentication = adminJwtTokenProvider.getAuthentication(token);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            LOGGER.info("[doFilterInternal] Admin token 유효성 체크 완료");
        }
        filterChain.doFilter(request, response);
    }
}
