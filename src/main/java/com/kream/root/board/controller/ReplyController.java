package com.kream.root.board.controller;

import com.kream.root.board.service.ReplyService;
import com.kream.root.entity.Reply;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/reply")
public class ReplyController {
    @Autowired
    private ReplyService replyService;

    @PostMapping
    public ResponseEntity<Reply> createReply(@RequestBody Reply reply) {
        return ResponseEntity.ok(replyService.saveOrUpdateReply(reply));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Reply> getReplyById(@PathVariable Long id) {
        Optional<Reply> reply = replyService.getReplyById(id);
        return reply.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Reply>> getAllReplies() {
        return ResponseEntity.ok(replyService.getAllReplies());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReply(@PathVariable Long id) {
        replyService.deleteReply(id);
        return ResponseEntity.noContent().build();
    }

}
