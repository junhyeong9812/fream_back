package com.kream.root.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

//@Entity
//@Getter
//@Setter
//@NoArgsConstructor
//@AllArgsConstructor
//@ToString
//@Table(name = "userlist")
//@SequenceGenerator(
//        name = "userlist_seq_generator",
//        sequenceName = "userlist_seq",
//        allocationSize = 1
//)
public class UserList {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "userlist_seq_generator")
    private Long ulid;

    @Column(unique = true, nullable = false)
    private String userid;

    @Column(nullable = false)
    private String userpw;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String gender;

    @Column(nullable = false)
    private Integer age;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String phone;

    private String profileUrl;

    private String userSize;

    private String userBio;

    @Column(name = "join_date", nullable = false)
    private LocalDateTime joinDate = LocalDateTime.now();

    @Column(name = "last_login_time", nullable = false)
    private LocalDateTime lastLoginTime = LocalDateTime.now();

    @Column(nullable = false, columnDefinition = "CHAR(1) DEFAULT '0'")
    private char receiveEmail = '0';

    @Column(nullable = false, columnDefinition = "CHAR(1) DEFAULT '0'")
    private char receiveMessage = '0';

    @Lob
    private String blockedProfiles;

    @Lob
    private String favoriteProducts;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<AddressBook> addressBooks;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<SellerProduct> sellerProducts;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Orders> orders;
}
