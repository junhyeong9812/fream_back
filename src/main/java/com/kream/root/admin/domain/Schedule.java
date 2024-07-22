package com.kream.root.admin.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "start_date")
    private String start;

    @Column(name = "end_date")
    private String end;

    @Column(name = "user_id")
    private String userId;//어드민 id

    public Schedule(String title, String start, String end, String userId) {
        this.title = title;
        this.start = start;
        this.end = end;
        this.userId = userId;
    }
}
