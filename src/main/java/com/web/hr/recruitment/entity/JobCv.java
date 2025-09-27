package com.web.hr.recruitment.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "job_cv")
public class JobCv {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer applicationId;

    @ManyToOne
    @JoinColumn(name = "cv_id", nullable = false)
    private Cv cv;

    @ManyToOne
    @JoinColumn(name = "job_id", nullable = false)
    private Job job;

    private String status = "pending"; // pending, accepted, rejected
    private LocalDateTime appliedAt = LocalDateTime.now();
    private LocalDateTime updatedDate = LocalDateTime.now();
    @Column(columnDefinition = "TEXT")
    private String feedback;

    // getters and setters
}
