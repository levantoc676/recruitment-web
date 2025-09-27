package com.web.hr.recruitment.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "cv")
public class Cv {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cvId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private String title;
    @Column(columnDefinition = "TEXT")
    private String education;
    @Column(columnDefinition = "TEXT")
    private String experience;
    @Column(columnDefinition = "TEXT")
    private String skills;
    @Column(columnDefinition = "TEXT")
    private String certifications;

    private LocalDateTime createDate = LocalDateTime.now();
    private LocalDateTime updatedDate = LocalDateTime.now();
    private Boolean delFlg = true;

    @OneToMany(mappedBy = "cv")
    private List<JobCv> applications;

    // getters and setters
}
