package com.kream.root.entity;


import lombok.*;
import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.kream.root.Login.model.UserListDTO;
import com.kream.root.MainAndShop.domain.Product;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "STYLES")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Style {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "style_seq_gen")
    @SequenceGenerator(name = "style_seq_gen", sequenceName = "STYLE_SEQ", allocationSize = 1)
    private Long id;

    @OneToMany(mappedBy = "style", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private Set<StyleLike> likes = new HashSet<>();

    @OneToMany(mappedBy = "style", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private Set<StyleReply> replies = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "USERID")
    private UserListDTO user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(name = "content")
    private String content;

    @Column(name = "style_date")
    private LocalDateTime styleDate;
}

//package com.kream.root.entity;
//
//import com.fasterxml.jackson.annotation.JsonIdentityInfo;
//import com.fasterxml.jackson.annotation.JsonManagedReference;
//import com.fasterxml.jackson.annotation.ObjectIdGenerators;
//import com.kream.root.Login.model.UserListDTO;
//import com.kream.root.MainAndShop.domain.Product;
//import jakarta.persistence.*;
//import lombok.AllArgsConstructor;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//
//import java.time.LocalDateTime;
//import java.util.HashSet;
//import java.util.Set;
//
//@Setter
//@Getter
//@Entity
//@NoArgsConstructor
//@AllArgsConstructor
//@Table(name = "styles")
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
//public class Style {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @OneToMany(mappedBy = "style", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    @JsonManagedReference
//    private Set<StyleLike> likes = new HashSet<>();
//
//    @OneToMany(mappedBy = "style", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    @JsonManagedReference
//    private Set<StyleReply> replies = new HashSet<>();
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "user_id", referencedColumnName = "USERID")
//    private UserListDTO user;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "product_id")
//    private Product product;
//
//    @Column(name = "content")
//    private String content;
//
//    @Column(name = "style_date")
//    private LocalDateTime styleDate;
//
//}