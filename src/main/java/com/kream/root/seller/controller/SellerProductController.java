package com.kream.root.seller.controller;

import com.kream.root.entity.SellerProduct;
import com.kream.root.seller.DTO.SellerProductRequest;
import com.kream.root.seller.service.SellerProductService;
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

    @PostMapping("/create")
    public ResponseEntity<?> createSellerProduct(@RequestBody SellerProductRequest sellerProductRequest, Authentication authentication) {
        try {
            String userId = ((UserDetails) authentication.getPrincipal()).getUsername();
            SellerProduct sellerProduct = sellerProductService.createSellerProduct(sellerProductRequest, userId);
            return ResponseEntity.ok(sellerProduct);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
    @GetMapping("/sellList")
    public ResponseEntity<?> getSellerProductsByUserId(Authentication authentication) {
        try {
            String userId = ((UserDetails) authentication.getPrincipal()).getUsername();
            List<SellerProduct> sellerProducts = sellerProductService.getSellerProductsByUserId(userId);
            return ResponseEntity.ok(sellerProducts);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}