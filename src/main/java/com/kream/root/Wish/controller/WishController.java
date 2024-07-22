package com.kream.root.Wish.controller;

import com.kream.root.Wish.service.WishService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    public WishController(WishService wishService) {
        this.wishService = wishService;
    }

    @PostMapping("/toggle/{productId}/{size}")
    public ResponseEntity<?> toggleWish(@PathVariable Long productId, @PathVariable String size, Authentication authentication) {
        // Authentication 객체에서 UserDetails를 추출하여 사용자 ID를 얻습니다.
        String userId = ((UserDetails) authentication.getPrincipal()).getUsername();

        boolean isAdded = wishService.toggleWish(userId, productId,size);
        return ResponseEntity.ok(isAdded ? "Product added to wish list" : "Product removed from wish list");
    }
}
