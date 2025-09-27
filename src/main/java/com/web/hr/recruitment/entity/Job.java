package com.web.hr.recruitment.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "jobs")
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer jobId;

    @ManyToOne
    @JoinColumn(name = "employer_id", nullable = false)
    private User employer;

    private String title;
    @Column(columnDefinition = "TEXT")
    private String description;
    @Column(columnDefinition = "TEXT")
    private String requirements;
    private String location;
    private String salaryRange;
    private String jobType; // full-time, part-time, intern

    private LocalDateTime createDate = LocalDateTime.now();
    private LocalDateTime updatedDate = LocalDateTime.now();
    private Boolean delFlg = true;

    @OneToMany(mappedBy = "job")
    private List<JobCv> applications;

    // getters and setters
}
