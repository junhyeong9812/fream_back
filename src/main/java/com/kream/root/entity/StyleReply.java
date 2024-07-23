package com.kream.root.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.kream.root.Login.model.UserListDTO;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "STYLE_REPLIES")
@SequenceGenerator(name = "style_reply_seq_gen", sequenceName = "STYLE_REPLY_SEQ", allocationSize = 1)
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class StyleReply {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "style_reply_seq_gen")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "style_id",
            foreignKey = @ForeignKey(name = "FK_STYLE_REPLY_STYLE"))
    @JsonBackReference("style-styleReplies")
    private Style style;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "USERID",
            foreignKey = @ForeignKey(name = "FK_STYLE_REPLY_USER"))
    @JsonBackReference("user-styleReply")
    private UserListDTO user;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private LocalDateTime createdDate;
}

//package com.kream.root.entity;
//
//import com.fasterxml.jackson.annotation.JsonIdentityInfo;
//import com.fasterxml.jackson.annotation.ObjectIdGenerators;
//import com.kream.root.Login.model.UserListDTO;
//import jakarta.persistence.*;
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//import java.time.LocalDateTime;
//
//@Entity
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
//@Table(name = "style_replies")
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
//public class StyleReply {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "style_id")
//    private Style style;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "user_id", referencedColumnName = "USERID")
//    private UserListDTO user;
//
//    @Column(nullable = false)
//    private String content;
//
//    @Column(nullable = false)
//    private LocalDateTime createdDate;
//}
