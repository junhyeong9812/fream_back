package com.kream.root.board.controller;
import com.kream.root.board.service.BoardLikesService;
import com.kream.root.entity.BoardLikes;
import com.kream.root.entity.BoardLikesId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/board-likes")
public class BoardLikesController {
    @Autowired
    private BoardLikesService boardLikesService;

    @PostMapping
    public ResponseEntity<BoardLikes> saveLike(@RequestBody BoardLikes boardLikes) {
        return ResponseEntity.ok(boardLikesService.saveLike(boardLikes));
    }

    @GetMapping("/{boardId}/{userId}")
    public ResponseEntity<BoardLikes> getLike(@PathVariable Long boardId, @PathVariable Long userId) {
        return boardLikesService.getLike(new BoardLikesId(boardId, userId))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{boardId}/{userId}")
    public ResponseEntity<Void> deleteLike(@PathVariable Long boardId, @PathVariable Long userId) {
        boardLikesService.deleteLike(new BoardLikesId(boardId, userId));
        return ResponseEntity.noContent().build();
    }

}
