package com.kream.root.style.controller;

import com.kream.root.Login.jwt.JwtTokenProvider;
import com.kream.root.entity.Style;
import com.kream.root.entity.StyleReply;
import com.kream.root.style.DTO.StyleDTO;
import com.kream.root.style.service.StyleService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/styles")
public class StyleController {
    private final String uploadDir = "C:/jsp_file/style/";

    private final StyleService styleService;
    private final JwtTokenProvider jwtTokenProvider;

    @Autowired
    public StyleController(StyleService styleService, JwtTokenProvider jwtTokenProvider) {
        this.styleService = styleService;
        this.jwtTokenProvider=jwtTokenProvider;
    }
    @GetMapping
    public ResponseEntity<List<StyleDTO>> getAllStyles() {
        List<StyleDTO> styles = styleService.getAllStyles();
        return ResponseEntity.ok(styles);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Style> getStyleById(@PathVariable Long id) {
        Optional<Style> style = styleService.getStyleById(id);
        return style.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }


//    @PostMapping
//    public ResponseEntity<Style> createStyle(@RequestParam Long productId,
//                                             @RequestParam String content,
//                                             @RequestPart(required = false) MultipartFile image,
//                                             Authentication authentication) {
//        String userId = authentication.getName();
//        Style style = styleService.createStyle(userId, productId, content, image);
//        return ResponseEntity.ok(style);
//    }
@PostMapping
public ResponseEntity<Style> createStyle(@RequestParam Long productId,
                                         @RequestParam String content,
                                         @RequestPart(required = false) MultipartFile image,
                                         HttpServletRequest request) {
    String token = resolveToken(request);
    if (token != null && jwtTokenProvider.validateToken(token)) {
        String userId = jwtTokenProvider.getUsername(token);
        Style style = styleService.createStyle(userId, productId, content, image);
        return ResponseEntity.ok(style);
    } else {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
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

//    @PostMapping("/like/{styleId}")
//    public ResponseEntity<Void> toggleLike(@PathVariable Long styleId, Authentication authentication) {
//        String userId = authentication.getName();
//        styleService.toggleLike(userId, styleId);
//        return ResponseEntity.noContent().build();
//    }
@PostMapping("/like/{styleId}")
public ResponseEntity<Void> toggleLike(@PathVariable Long styleId, HttpServletRequest request) {
    String token = resolveToken(request);
    if (token != null && jwtTokenProvider.validateToken(token)) {
        String userId = jwtTokenProvider.getUsername(token);
        styleService.toggleLike(userId, styleId);
        return ResponseEntity.noContent().build();
    } else {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
}

//    @PostMapping("/reply/{styleId}")
//    public ResponseEntity<StyleReply> addReply(@PathVariable Long styleId, @RequestParam String content, Authentication authentication) {
//        String userId = authentication.getName();
//        StyleReply reply = styleService.addReply(userId, styleId, content);
//        return ResponseEntity.ok(reply);
//    }
@PostMapping("/reply/{styleId}")
public ResponseEntity<StyleReply> addReply(@PathVariable Long styleId, @RequestParam String content, HttpServletRequest request) {
    String token = resolveToken(request);
    if (token != null && jwtTokenProvider.validateToken(token)) {
        String userId = jwtTokenProvider.getUsername(token);
        StyleReply reply = styleService.addReply(userId, styleId, content);
        return ResponseEntity.ok(reply);
    } else {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
}

    @DeleteMapping("/reply/{replyId}")
    public ResponseEntity<Void> deleteReply(@PathVariable Long replyId) {
        styleService.deleteReply(replyId);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/image/{filename:.+}")
    public ResponseEntity<Resource> serveFile(@PathVariable String filename) {
        try {
            Path file = Paths.get(uploadDir).resolve(filename);
            Resource resource = new UrlResource(file.toUri());

            if (resource.exists() || resource.isReadable()) {
                return ResponseEntity.ok()
                        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                        .body(resource);
            } else {
                throw new RuntimeException("Could not read the file!");
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("Error: " + e.getMessage());
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