package com.kream.root.entity;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ReplyLikesId {
    private Long replyId;
    private Long userId;

    // 기본 생성자
    public ReplyLikesId() {}

    // 파라미터를 받는 생성자
    public ReplyLikesId(Long replyId, Long userId) {
        this.replyId = replyId;
        this.userId = userId;
    }

    // Getters, Setters, equals, hashCode
    public Long getReplyId() {
        return replyId;
    }

    public void setReplyId(Long replyId) {
        this.replyId = replyId;
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
        ReplyLikesId that = (ReplyLikesId) o;
        return Objects.equals(replyId, that.replyId) && Objects.equals(userId, that.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(replyId, userId);
    }
}
