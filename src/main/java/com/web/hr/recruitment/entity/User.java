package com.web.hr.recruitment.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;

    @Column(nullable = false, unique = true, length = 50)
    private String username;

    @Column(nullable = false, length = 100)
    private String password;

    @Column(nullable = false, unique = true, length = 100)
    private String email;

    @Column(length = 100)
    private String fullName;

    @Column(nullable = false, length = 100)
    private String role; // candidate, employer, admin

    @Column(length = 20)
    private String phone;

    private LocalDateTime createDate = LocalDateTime.now();
    private LocalDateTime updatedDate = LocalDateTime.now();

    private Boolean delFlg = true;

    // Relationships
    @OneToMany(mappedBy = "user")
    private List<Cv> cvs;

    @OneToMany(mappedBy = "employer")
    private List<Job> jobs;

    @OneToMany(mappedBy = "admin")
    private List<AdminLog> adminLogs;

    // getters and setters
}
