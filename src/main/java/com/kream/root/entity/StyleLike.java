package com.kream.root.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.kream.root.Login.model.UserListDTO;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "STYLE_LIKES")
@SequenceGenerator(name = "style_like_seq_gen", sequenceName = "STYLE_LIKE_SEQ", allocationSize = 1)
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class StyleLike {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "style_like_seq_gen")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "style_id",
            foreignKey = @ForeignKey(name = "FK_STYLE_LIKE_STYLE"))
    @JsonBackReference("style-styleLike")
    private Style style;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "USERID",
            foreignKey = @ForeignKey(name = "FK_STYLE_LIKE_USER"))
    @JsonBackReference("user-styleLike")
    private UserListDTO user;
}


//package com.kream.root.entity;
//
//import com.fasterxml.jackson.annotation.JsonIdentityInfo;
//import com.fasterxml.jackson.annotation.ObjectIdGenerators;
//import com.kream.root.Login.model.UserListDTO;
//import jakarta.persistence.*;
//import lombok.*;
//
//
//@Entity
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
//@Table(name = "style_likes")
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
//public class StyleLike {
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
//}
