package com.web.hr.recruitment.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "cvs")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CV {

  // =============================
  // Khóa chính
  // =============================
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long cvId;

  // =============================
  // Thông tin cơ bản của CV
  // =============================
  @Column(nullable = false, length = 200)
  private String title; // Tên CV hoặc vị trí ứng tuyển

  @Column(nullable = false, length = 100)
  private String filePath; // Đường dẫn lưu file CV (PDF, DOCX...)

  @Column(length = 100)
  private String fileType; // Loại file: pdf, docx, ...

  @Column
  private Long fileSize; // Dung lượng file (KB, MB)

  @Column(columnDefinition = "TEXT")
  private String summary; // Mô tả tóm tắt bản thân

  // =============================
  // Thông tin quản lý
  // =============================
  private LocalDateTime createdDate = LocalDateTime.now(); // Thời gian tạo CV
  private LocalDateTime updatedDate = LocalDateTime.now(); // Thời gian cập nhật CV
  private Boolean delFlg = false; // Cờ xóa mềm (false = còn, true = xóa)

  // =============================
  // Quan hệ với User (ứng viên sở hữu CV)
  // =============================
  @ManyToOne
  @JoinColumn(name = "user_id", nullable = false)
  private User user; // Người sở hữu CV (mỗi CV thuộc về 1 User, 1 User có thể có nhiều CV)

  // =============================
  // Quan hệ với JobApplication
  // =============================
  @OneToMany(mappedBy = "cv")
  private List<JobApplication> jobApplications;
  // Danh sách các ứng tuyển liên quan đến CV này
}