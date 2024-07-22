package com.kream.root.entity;

import com.kream.root.Login.model.UserListDTO;
import jakarta.persistence.*;

@Entity
@Table(name = "REPLY_LIKES")
public class ReplyLikes {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "replyLikesSeqGen")
    @SequenceGenerator(name = "replyLikesSeqGen", sequenceName = "reply_likes_seq", allocationSize = 1)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "REPLY_ID")
    private Reply reply;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private UserListDTO user;

    @Column(name = "LIKE_DISLIKE")
    private char likeDislike;
}