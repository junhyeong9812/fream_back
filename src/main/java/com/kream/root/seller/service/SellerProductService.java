package com.kream.root.seller.service;

import com.kream.root.Login.model.UserListDTO;
import com.kream.root.Login.repository.UserListRepository;
import com.kream.root.MainAndShop.domain.Product;
import com.kream.root.MainAndShop.repository.ProductRepository;
import com.kream.root.admin.repository.SellerProductRepository;
import com.kream.root.entity.SellerProduct;

import com.kream.root.seller.DTO.SellerProductRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SellerProductService {

    @Autowired
    private SellerProductRepository sellerProductRepository;

    @Autowired
    private UserListRepository userListRepository;

    @Autowired
    private ProductRepository productRepository;

    public SellerProduct createSellerProduct(SellerProductRequest request, String userId) {
        UserListDTO user = userListRepository.findByUserId(userId).orElseThrow(() -> new RuntimeException("User not found"));
        Product product = productRepository.findByPrid(request.getProductId()).orElseThrow(() -> new RuntimeException("Product not found"));

        SellerProduct sellerProduct = new SellerProduct();
        sellerProduct.setUser(user);
        sellerProduct.setProduct(product);
        sellerProduct.setProSize(request.getProSize());
        sellerProduct.setAddress(request.getAddress());
        sellerProduct.setAccountHolder(request.getAccountHolder());
        sellerProduct.setBankName(request.getBankName());
        sellerProduct.setAccountNumber(request.getAccountNumber());
        sellerProduct.setName(request.getName());
        sellerProduct.setPhoneNumber(request.getPhoneNumber());
        sellerProduct.setIsSold('N');

        return sellerProductRepository.save(sellerProduct);
    }
    public List<SellerProduct> getSellerProductsByUserId(String userId) {
        UserListDTO user = userListRepository.findByUserId(userId).orElseThrow(() -> new RuntimeException("User not found"));
        return sellerProductRepository.findByUser(user);
    }

}