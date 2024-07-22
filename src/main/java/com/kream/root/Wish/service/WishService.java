package com.kream.root.Wish.service;


import com.kream.root.Login.model.UserListDTO;
import com.kream.root.Login.repository.UserListRepository;
import com.kream.root.MainAndShop.domain.Product;
import com.kream.root.MainAndShop.repository.ProductRepository;
import com.kream.root.Wish.repository.WishRepository;
import com.kream.root.entity.Wish;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class WishService {
    private final WishRepository wishRepository;
    private final ProductRepository productRepository;
    private final UserListRepository userListRepository;

    @Autowired
    public WishService(WishRepository wishRepository, ProductRepository productRepository,UserListRepository userListRepository) {
        this.wishRepository = wishRepository;
        this.productRepository = productRepository;
        this.userListRepository= userListRepository;
    }

    @Transactional
    public boolean toggleWish(String userId, Long productId, String size) {
        Optional<Product> product = productRepository.findById(productId);
        Optional<UserListDTO> user = userListRepository.findByUserId(userId);
        if (!product.isPresent()) {
            throw new IllegalArgumentException("Product not found with ID: " + productId);
        }
        if (!user.isPresent()) {
            throw new IllegalArgumentException("User not found with ID: " + userId);
        }

        Optional<Wish> existingWish = wishRepository.findByUserIdAndProductId(userId, productId);
        if (existingWish.isPresent()) {
            wishRepository.delete(existingWish.get());
            return false; // 제거됨
        } else {
            Wish newWish = new Wish();
            newWish.setUser(user.get());
            newWish.setProduct(product.get());
            newWish.setSize(size); // 사이즈 설정
            wishRepository.save(newWish);
            return true; // 추가됨
        }
    }
}
