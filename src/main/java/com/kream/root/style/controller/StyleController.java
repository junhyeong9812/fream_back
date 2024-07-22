package com.kream.root.style.controller;

import com.kream.root.entity.Style;
import com.kream.root.entity.StyleReply;
import com.kream.root.style.service.StyleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/styles")
public class StyleController {

    private final StyleService styleService;

    @Autowired
    public StyleController(StyleService styleService) {
        this.styleService = styleService;
    }

    @PostMapping
    public ResponseEntity<Style> createStyle(@RequestParam Long productId, @RequestParam String content, Authentication authentication) {
        String userId = authentication.getName();
        Style style = styleService.createStyle(userId, productId, content);
        return ResponseEntity.ok(style);
    }

    @PutMapping("/{styleId}")
    public ResponseEntity<Style> updateStyle(@PathVariable Long styleId, @RequestParam String content) {
        Optional<Style> styleOpt = styleService.updateStyle(styleId, content);
//        if (styleOpt.isPresent()) {
//            return ResponseEntity.ok(styleOpt.get());
//        } else {
//            return ResponseEntity.notFound().build();
//        }
        return styleService.updateStyle(styleId, content)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
//        styleService.updateStyle(styleId, content)는 Optional<Style>을 반환합니다.
//                map(ResponseEntity::ok)은 Optional에 값이 있을 때 실행되어 ResponseEntity.ok(style)을 반환합니다.
//        orElseGet(() -> ResponseEntity.notFound().build())은 Optional이 비어 있을 때 실행되어 ResponseEntity.notFound().build()를 반환합니다
    }

    @DeleteMapping("/{styleId}")
    public ResponseEntity<Void> deleteStyle(@PathVariable Long styleId) {
        styleService.deleteStyle(styleId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/like/{styleId}")
    public ResponseEntity<Void> toggleLike(@PathVariable Long styleId, Authentication authentication) {
        String userId = authentication.getName();
        styleService.toggleLike(userId, styleId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/reply/{styleId}")
    public ResponseEntity<StyleReply> addReply(@PathVariable Long styleId, @RequestParam String content, Authentication authentication) {
        String userId = authentication.getName();
        StyleReply reply = styleService.addReply(userId, styleId, content);
        return ResponseEntity.ok(reply);
    }

    @DeleteMapping("/reply/{replyId}")
    public ResponseEntity<Void> deleteReply(@PathVariable Long replyId) {
        styleService.deleteReply(replyId);
        return ResponseEntity.noContent().build();
    }
}