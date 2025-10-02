package com.web.hr.recruitment.entity;

import com.web.hr.recruitment.entity.user.User;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "admin_logs")
public class AdminLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer logId;

    @ManyToOne
    @JoinColumn(name = "admin_id", nullable = false)
    private User admin;

    private String action;
    private String targetType; // user, cv, job
    private Integer targetId;
    private LocalDateTime createDate = LocalDateTime.now();

    // getters and setters
}
