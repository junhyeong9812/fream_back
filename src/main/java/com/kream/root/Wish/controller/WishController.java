package com.kream.root.Wish.controller;

import com.kream.root.Login.jwt.JwtTokenProvider;
import com.kream.root.Wish.service.WishService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/wishes")
public class WishController {
    private final WishService wishService;
    private final JwtTokenProvider jwtTokenProvider;

    @Autowired
    public WishController(WishService wishService, JwtTokenProvider jwtTokenProvider) {
        this.wishService = wishService;
        this.jwtTokenProvider = jwtTokenProvider;
    }

//    @PostMapping("/toggle/{productId}/{size}")
//    public ResponseEntity<?> toggleWish(@PathVariable Long productId, @PathVariable String size, Authentication authentication) {
//        // Authentication 객체에서 UserDetails를 추출하여 사용자 ID를 얻습니다.
//        String userId = ((UserDetails) authentication.getPrincipal()).getUsername();
//
//        boolean isAdded = wishService.toggleWish(userId, productId,size);
//        return ResponseEntity.ok(isAdded ? "Product added to wish list" : "Product removed from wish list");
//    }
@PostMapping("/toggle/{productId}/{size}")
public ResponseEntity<?> toggleWish(@PathVariable Long productId, @PathVariable String size, HttpServletRequest request) {
    String token = resolveToken(request);
    if (token != null && jwtTokenProvider.validateToken(token)) {
        String userId = jwtTokenProvider.getUsername(token);
        boolean isAdded = wishService.toggleWish(userId, productId, size);
        return ResponseEntity.ok(isAdded ? "Product added to wish list" : "Product removed from wish list");
    } else {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
}

    private String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }
}
