package com.kream.root.board.controller;

import com.kream.root.board.service.ReplyLikesService;
import com.kream.root.entity.ReplyLikes;
import com.kream.root.entity.ReplyLikesId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reply-likes")
public class ReplyLikesController {
    @Autowired
    private ReplyLikesService replyLikesService;

    @PostMapping
    public ResponseEntity<ReplyLikes> saveLike(@RequestBody ReplyLikes replyLikes) {
        return ResponseEntity.ok(replyLikesService.saveLike(replyLikes));
    }

    @GetMapping("/{replyId}/{userId}")
    public ResponseEntity<ReplyLikes> getLike(@PathVariable Long replyId, @PathVariable Long userId) {
        return replyLikesService.getLike(new ReplyLikesId(replyId, userId))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{replyId}/{userId}")
    public ResponseEntity<Void> deleteLike(@PathVariable Long replyId, @PathVariable Long userId) {
        replyLikesService.deleteLike(new ReplyLikesId(replyId, userId));
        return ResponseEntity.noContent().build();
    }
}
