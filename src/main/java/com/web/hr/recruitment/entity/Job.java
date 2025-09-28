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

  public Boolean getDelFlg() {
    return delFlg;
  }

  public void setDelFlg(Boolean delFlg) {
    this.delFlg = delFlg;
  }

  public LocalDateTime getUpdatedDate() {
    return updatedDate;
  }

  public void setUpdatedDate(LocalDateTime updatedDate) {
    this.updatedDate = updatedDate;
  }

  public LocalDateTime getCreateDate() {
    return createDate;
  }

  public void setCreateDate(LocalDateTime createDate) {
    this.createDate = createDate;
  }

  public String getJobType() {
    return jobType;
  }

  public void setJobType(String jobType) {
    this.jobType = jobType;
  }

  public String getSalaryRange() {
    return salaryRange;
  }

  public void setSalaryRange(String salaryRange) {
    this.salaryRange = salaryRange;
  }

  public String getLocation() {
    return location;
  }

  public void setLocation(String location) {
    this.location = location;
  }

  public String getRequirements() {
    return requirements;
  }

  public void setRequirements(String requirements) {
    this.requirements = requirements;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public User getEmployer() {
    return employer;
  }

  public void setEmployer(User employer) {
    this.employer = employer;
  }

  public Integer getJobId() {
    return jobId;
  }

  public void setJobId(Integer jobId) {
    this.jobId = jobId;
  }
}
