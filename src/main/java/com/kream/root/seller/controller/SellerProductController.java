package com.kream.root.seller.controller;

import com.kream.root.Login.jwt.JwtTokenProvider;
import com.kream.root.entity.SellerProduct;
import com.kream.root.seller.DTO.SellerProductRequest;
import com.kream.root.seller.service.SellerProductService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sellerProducts")
public class SellerProductController {

    @Autowired
    private SellerProductService sellerProductService;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;

//    @PostMapping("/create")
//    public ResponseEntity<?> createSellerProduct(@RequestBody SellerProductRequest sellerProductRequest, Authentication authentication) {
//        try {
//            String userId = ((UserDetails) authentication.getPrincipal()).getUsername();
//            SellerProduct sellerProduct = sellerProductService.createSellerProduct(sellerProductRequest, userId);
//            return ResponseEntity.ok(sellerProduct);
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
//        }
//    }
//    @GetMapping("/sellList")
//    public ResponseEntity<?> getSellerProductsByUserId(Authentication authentication) {
//        try {
//            String userId = ((UserDetails) authentication.getPrincipal()).getUsername();
//            List<SellerProduct> sellerProducts = sellerProductService.getSellerProductsByUserId(userId);
//            return ResponseEntity.ok(sellerProducts);
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
//        }
//    }
@PostMapping("/create")
public ResponseEntity<?> createSellerProduct(@RequestBody SellerProductRequest sellerProductRequest, HttpServletRequest request) {
    try {
        String token = resolveToken(request);
        // token을 이용하여 사용자 정보를 검증하고 사용자 ID를 가져옵니다.
        String userId = jwtTokenProvider.getUsername(token); // 이 메소드는 토큰에서 사용자 ID를 추출하는 로직을 구현해야 합니다.
        SellerProduct sellerProduct = sellerProductService.createSellerProduct(sellerProductRequest, userId);
        return ResponseEntity.ok(sellerProduct);
    } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
    }
}

    @GetMapping("/sellList")
    public ResponseEntity<?> getSellerProductsByUserId(HttpServletRequest request) {
        try {
            String token = resolveToken(request);
            String userId = jwtTokenProvider.getUsername(token); // 이 메소드는 토큰에서 사용자 ID를 추출하는 로직을 구현해야 합니다.
            List<SellerProduct> sellerProducts = sellerProductService.getSellerProductsByUserId(userId);
            return ResponseEntity.ok(sellerProducts);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
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