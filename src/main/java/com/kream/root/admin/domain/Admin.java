package com.kream.root.admin.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;
    private String name;
    private int age;
//    private String phoneNumber;
@Column(name = "PHONE_NUMBER")
private String phoneNumber;
    private String email;
    @Column(name = "ACCESS_LEVEL")
    private String accessLevel;
//    private String accessLevel;
    private String role;
    private String status;
    @Column(name = "CREATED_AT")
    private java.sql.Timestamp createdAt;

    @Column(name = "UPDATED_AT")
    private java.sql.Timestamp updatedAt;

    @Column(name = "LAST_LOGIN")
    private java.sql.Timestamp lastLogin;

    @Column(name = "PROFILE_PICTURE")
    private String profilePicture;
//    private java.sql.Timestamp createdAt;
//    private java.sql.Timestamp updatedAt;
//    private java.sql.Timestamp lastLogin;
//    private String profilePicture;
    private String department;
    private String notes;

    @Embedded
    private Address address;

    public Admin(String username, String password, String name, int age, String phoneNumber, String email,
                 String accessLevel, String role, String status, java.sql.Timestamp createdAt, java.sql.Timestamp updatedAt,
                 java.sql.Timestamp lastLogin, String profilePicture, String department, String notes, Address address) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.age = age;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.accessLevel = accessLevel;
        this.role = role;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.lastLogin = lastLogin;
        this.profilePicture = profilePicture;
        this.department = department;
        this.notes = notes;
        this.address = address;
    }

}