package com.kream.root.style.service;

import com.kream.root.Login.model.UserListDTO;
import com.kream.root.Login.repository.UserListRepository;
import com.kream.root.MainAndShop.domain.Product;
import com.kream.root.MainAndShop.repository.ProductRepository;
import com.kream.root.entity.Style;
import com.kream.root.entity.StyleLike;
import com.kream.root.entity.StyleReply;
import com.kream.root.style.repository.StyleLikeRepository;
import com.kream.root.style.repository.StyleReplyRepository;
import com.kream.root.style.repository.StyleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class StyleService {

    private final StyleRepository styleRepository;
    private final StyleLikeRepository styleLikeRepository;
    private final StyleReplyRepository styleReplyRepository;
    private final UserListRepository userRepository;
    private final ProductRepository productRepository;

    @Autowired
    public StyleService(StyleRepository styleRepository, StyleLikeRepository styleLikeRepository,
                        StyleReplyRepository styleReplyRepository, UserListRepository userRepository,
                        ProductRepository productRepository) {
        this.styleRepository = styleRepository;
        this.styleLikeRepository = styleLikeRepository;
        this.styleReplyRepository = styleReplyRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }

    @Transactional
    public Style createStyle(String userId, Long productId, String content) {
        Optional<UserListDTO> user = userRepository.findByUserId(userId);
        Optional<Product> product = productRepository.findById(productId);

        if (user.isPresent() && product.isPresent()) {
            Style style = new Style();
            style.setUser(user.get());
            style.setProduct(product.get());
            style.setContent(content);
            style.setStyleDate(LocalDateTime.now());
            return styleRepository.save(style);
        } else {
            throw new IllegalArgumentException("User or Product not found");
        }
    }

    @Transactional
    public Optional<Style> updateStyle(Long styleId, String content) {
        styleRepository.updateStyleContent(styleId, content);
        return styleRepository.findById(styleId);
    }

    @Transactional
    public void deleteStyle(Long styleId) {
        styleRepository.deleteStyle(styleId);
    }

    @Transactional
    public void toggleLike(String userId, Long styleId) {
        Optional<UserListDTO> user = userRepository.findByUserId(userId);
        Optional<Style> style = styleRepository.findById(styleId);

        if (user.isPresent() && style.isPresent()) {//isPresent는 객체가 존재하면 true 없으면 false를 반환
            Optional<StyleLike> styleLike = styleLikeRepository.findByUserAndStyle(user.get(), style.get());
            if (styleLike.isPresent()) {
                styleLikeRepository.delete(styleLike.get());
            } else {
                StyleLike newStyleLike = new StyleLike();
                newStyleLike.setUser(user.get());
                newStyleLike.setStyle(style.get());
                styleLikeRepository.save(newStyleLike);
            }
        } else {
            throw new IllegalArgumentException("User or Style not found");
        }
    }

    @Transactional
    public StyleReply addReply(String userId, Long styleId, String content) {
        Optional<UserListDTO> user = userRepository.findByUserId(userId);
        Optional<Style> style = styleRepository.findById(styleId);

        if (user.isPresent() && style.isPresent()) {
            StyleReply reply = new StyleReply();
            reply.setUser(user.get());
            reply.setStyle(style.get());
            reply.setContent(content);
            reply.setCreatedDate(LocalDateTime.now());
            return styleReplyRepository.save(reply);
        } else {
            throw new IllegalArgumentException("User or Style not found");
        }
    }

    @Transactional
    public void deleteReply(Long replyId) {
        StyleReply reply = styleReplyRepository.findById(replyId);
        if (reply != null) {
            styleReplyRepository.delete(reply);
        }
    }
}
