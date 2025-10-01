package com.web.hr.recruitment.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "jobs")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Job {

  // =============================
  // Khóa chính
  // =============================
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long jobId;   // ID của Job

  // =============================
  // Thông tin công ty
  // =============================
  @Column(name = "company_name")
  private String companyName; // Tên công ty

  @Column(name = "company_logo")
  private String companyLogo; // Logo công ty

  @Column(name = "company_address")
  private String companyAddress; // Địa chỉ công ty

  @Column(name = "company_website")
  private String companyWebsite; // Website công ty

  // =============================
  // Nội dung công việc
  // =============================
  @Column(nullable = false)
  private String title; // Tiêu đề công việc

  @Column(columnDefinition = "TEXT")
  private String description; // Mô tả công việc

  @Column(columnDefinition = "TEXT")
  private String requirements; // Yêu cầu công việc

  @Column(columnDefinition = "TEXT")
  private String skills; // Kỹ năng yêu cầu

  @Column(columnDefinition = "TEXT")
  private String benefits; // Quyền lợi, phúc lợi

  // =============================
  // Thông tin tuyển dụng
  // =============================
  private String location; // Địa điểm làm việc

  @Column(name = "work_mode")
  private String workMode;   // Hình thức làm việc: Onsite, Hybrid, Remote

  @Column(name = "job_type")
  private String jobType;    // Loại công việc: Full-time, Part-time, Intern...

  private String industry; // Ngành nghề

  @Column(name = "salary_range")
  private String salaryRange; // Mức lương dự kiến

  @Column(name = "experience_level")
  private String experienceLevel; // Kinh nghiệm yêu cầu

  @Column(name = "education_level")
  private String educationLevel; // Trình độ học vấn

  private LocalDate deadline; // Hạn nộp hồ sơ

  @Column(name = "logo_path")
  private String logoPath; // Đường dẫn logo công ty (nếu có)

  // =============================
  // Trạng thái & thống kê
  // =============================
  @Column(name = "employment_status")
  private String employmentStatus = "Open"; // Trạng thái tuyển dụng: Open/Closed

  private Integer views = 0; // Số lượt xem job

  @Column(name = "applications_count")
  private Integer applicationsCount = 0; // Số lượt ứng tuyển

  @Column(name = "del_flg")
  private Boolean delFlg = false; // Cờ xóa mềm

  // =============================
  // Audit
  // =============================
  @Column(name = "create_date", updatable = false)
  private LocalDateTime createDate; // Ngày tạo

  @Column(name = "updated_date")
  private LocalDateTime updatedDate; // Ngày cập nhật

  // =============================
  // Quan hệ với User (Employer)
  // =============================
  @ManyToOne
  @JoinColumn(name = "user_id", nullable = false)
  private User user; // Nhà tuyển dụng tạo job

  // =============================
  // Quan hệ với JobApplication
  // =============================
  @OneToMany(mappedBy = "job")
  private List<JobApplication> applications; // Các ứng tuyển cho job này
  private Integer headcount;
  private String applicationMethod;

  @Column(name = "contact_email")
  private String contactEmail;

  @Column(name = "contact_phone")
  private String contactPhone;

  // =============================
  // Callback tự động set ngày giờ
  // =============================
  @PrePersist
  public void prePersist() {
    this.createDate = LocalDateTime.now();
    this.updatedDate = LocalDateTime.now();
  }

  @PreUpdate
  public void preUpdate() {
    this.updatedDate = LocalDateTime.now();
  }

}