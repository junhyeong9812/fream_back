package com.kream.root.entity;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class BoardLikesId implements Serializable {
    private Long boardId;
    private Long userId;

    public BoardLikesId() {}

    // 파라미터를 받는 생성자
    public BoardLikesId(Long boardId, Long userId) {
        this.boardId = boardId;
        this.userId = userId;
    }

    // Getters, Setters, equals, hashCode
    public Long getBoardId() {
        return boardId;
    }

    public void setBoardId(Long boardId) {
        this.boardId = boardId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BoardLikesId that = (BoardLikesId) o;
        return Objects.equals(boardId, that.boardId) && Objects.equals(userId, that.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(boardId, userId);
    }
}