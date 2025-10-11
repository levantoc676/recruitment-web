package com.web.hr.recruitment.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "job_applications")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JobApplication {

  // =============================
  // Khóa chính
  // =============================
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id; // ID của ứng tuyển

  // =============================
  // Quan hệ với CV
  // =============================
  @ManyToOne
  @JoinColumn(name = "cv_id", nullable = false)
  private CV cv; // CV được nộp

  // =============================
  // Quan hệ với Job
  // =============================
  @ManyToOne
  @JoinColumn(name = "job_id", nullable = false)
  private Job job; // Job mà ứng viên ứng tuyển

  // =============================
  // Trạng thái ứng tuyển
  // =============================
  @Column(nullable = false, length = 50)
  private String status;
  // Ví dụ: PENDING, REVIEWED, ACCEPTED, REJECTED

  // =============================
  // Thời điểm ứng tuyển
  // =============================
  @Column(name = "applied_date", nullable = false)
  private LocalDateTime appliedDate; // Thời gian nộp hồ sơ

  // =============================
  // Ghi chú bổ sung
  // =============================
  @Column(columnDefinition = "TEXT")
  private String note; // Ghi chú thêm từ ứng viên hoặc HR
}
