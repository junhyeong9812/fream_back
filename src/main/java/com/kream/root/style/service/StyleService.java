package com.kream.root.style.service;

import com.kream.root.Login.model.UserListDTO;
import com.kream.root.Login.repository.UserListRepository;
import com.kream.root.MainAndShop.domain.Product;
import com.kream.root.MainAndShop.repository.ProductRepository;
import com.kream.root.entity.Style;
import com.kream.root.entity.StyleLike;
import com.kream.root.entity.StyleReply;
import com.kream.root.style.DTO.StyleDTO;
import com.kream.root.style.repository.StyleLikeRepository;
import com.kream.root.style.repository.StyleReplyRepository;
import com.kream.root.style.repository.StyleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

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
    public Optional<Style> getStyleById(Long id) {
        return styleRepository.findById(id);
    }

    @Transactional
    public Style createStyle(String userId, Long productId, String content, MultipartFile image) {
        Optional<UserListDTO> user = userRepository.findByUserId(userId);
        Optional<Product> product = productRepository.findById(productId);

        if (user.isPresent() && product.isPresent()) {
            Style style = new Style();
            style.setUser(user.get());
            style.setProduct(product.get());
            style.setContent(content);
            style.setStyleDate(LocalDateTime.now());
            if (image != null && !image.isEmpty()) {
                String imgName = saveImage(image, userId);
                style.setStyleImgName(imgName);
            }
            return styleRepository.save(style);
        } else {
            throw new IllegalArgumentException("User or Product not found");
        }
    }
    private String saveImage(MultipartFile image, String userId) {
        String uploadDir = "C:/jsp_file/style/"; // 실제 파일 저장 경로로 수정
        File dir = new File(uploadDir);
        if (!dir.exists()) {
            dir.mkdirs(); // 디렉토리가 없으면 생성
        }

        String originalFilename = image.getOriginalFilename();
        String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
        String newFilename = userId + "_" + UUID.randomUUID().toString() + extension;

        try {
            File file = new File(uploadDir + newFilename);
            image.transferTo(file);
        } catch (IOException e) {
            throw new RuntimeException("이미지 저장에 실패했습니다.", e);
        }

        return newFilename;
    }
    public List<StyleDTO> getAllStyles() {
        return styleRepository.findAll().stream()
                .map(style -> StyleDTO.builder()
                        .id(style.getId())
                        .imageUrl( "http://localhost:3000/styles/image/"+style.getStyleImgName()) // 이미지 저장 경로에 맞게 변경
                        .profileUrl(style.getUser().getProfileUrl())
                        .username(style.getUser().getUserName())
                        .content(style.getContent())
                        .build())
                .collect(Collectors.toList());
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
