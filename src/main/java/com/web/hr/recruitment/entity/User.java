package com.web.hr.recruitment.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
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
  
  public Integer getUserId() {
    return userId;
  }

  public void setUserId(Integer userId) {
    this.userId = userId;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getFullName() {
    return fullName;
  }

  public void setFullName(String fullName) {
    this.fullName = fullName;
  }

  public String getRole() {
    return role;
  }

  public void setRole(String role) {
    this.role = role;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public LocalDateTime getCreateDate() {
    return createDate;
  }

  public void setCreateDate(LocalDateTime createDate) {
    this.createDate = createDate;
  }

  public LocalDateTime getUpdatedDate() {
    return updatedDate;
  }

  public void setUpdatedDate(LocalDateTime updatedDate) {
    this.updatedDate = updatedDate;
  }

  public Boolean getDelFlg() {
    return delFlg;
  }

  public void setDelFlg(Boolean delFlg) {
    this.delFlg = delFlg;
  }

  public List<Cv> getCvs() {
    return cvs;
  }

  public void setCvs(List<Cv> cvs) {
    this.cvs = cvs;
  }

  public List<Job> getJobs() {
    return jobs;
  }

  public void setJobs(List<Job> jobs) {
    this.jobs = jobs;
  }

  public List<AdminLog> getAdminLogs() {
    return adminLogs;
  }

  public void setAdminLogs(List<AdminLog> adminLogs) {
    this.adminLogs = adminLogs;
  }
}
