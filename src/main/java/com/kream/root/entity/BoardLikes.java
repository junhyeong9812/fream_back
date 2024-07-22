package com.kream.root.entity;


import com.kream.root.Login.model.UserListDTO;
import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "BOARD_LIKES")
public class BoardLikes {
    @EmbeddedId
    private BoardLikesId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("boardId")
    @JoinColumn(name = "BOARD_ID")
    private Board board;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("userId")
    @JoinColumn(name = "USER_ID")
    private UserListDTO user;

    @Column(name = "LIKE_DISLIKE")
    private char likeDislike;

    // Getters and Setters
}

