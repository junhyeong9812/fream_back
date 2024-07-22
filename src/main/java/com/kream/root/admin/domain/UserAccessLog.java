package com.kream.root.admin.domain;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "user_access_log")
@NoArgsConstructor
@Getter @Setter
public class UserAccessLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String refererUrl;
    private String userAgent;
    private String os;
    private String browser;
    private String deviceType;
    private LocalDateTime accessTime = LocalDateTime.now();
}
