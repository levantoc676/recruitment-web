package com.web.hr.recruitment.entity.user;

import com.web.hr.recruitment.entity.AdminLog;
import com.web.hr.recruitment.entity.CV;
import com.web.hr.recruitment.entity.Job;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

  // =============================
  // Khóa chính
  // =============================
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long userId;

  // =============================
  // Thông tin cơ bản của User
  // =============================
  @Column(nullable = false, unique = true, length = 50)
  private String username; // Tên đăng nhập

  @Column(nullable = false, length = 100)
  private String password; // Mật khẩu (nên mã hóa)

  @Column(nullable = false, unique = true, length = 100)
  private String email; // Email của user

  @Column(length = 100)
  private String fullName; // Họ và tên đầy đủ

  @Column(nullable = false, length = 100)
  private String role; // Vai trò: candidate, employer, admin

  @Column(length = 20)
  private String phone; // Số điện thoại

  // =============================
  // Thông tin quản lý
  // =============================
  private LocalDateTime createDate = LocalDateTime.now(); // Ngày tạo
  private LocalDateTime updatedDate = LocalDateTime.now(); // Ngày cập nhật
  @Column(nullable = false)
  private Boolean delFlg = true; // Cờ xóa mềm (true = xóa, false = còn)
  @Column(nullable = false)
  private Boolean locked = false; // false = tài khoản chưa bị khóa, true = bị khóa
  // =============================
  // Quan hệ với CV
  // =============================
  @OneToMany(mappedBy = "user")
  private List<CV> cvs; // Danh sách CV của user (ứng viên)

  // =============================
  // Quan hệ với Job
  // =============================
  @OneToMany(mappedBy = "user")
  private List<Job> jobs; // Danh sách Job do user (employer) tạo

  // =============================
  // Quan hệ với AdminLog
  // =============================
  @OneToMany(mappedBy = "admin")
  private List<AdminLog> adminLogs; // Các log do admin thực hiện
}
