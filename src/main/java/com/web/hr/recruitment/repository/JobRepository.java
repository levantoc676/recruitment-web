package com.web.hr.recruitment.repository;

import com.web.hr.recruitment.entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobRepository extends JpaRepository<Job, Long> {
  // Có thể thêm custom query nếu cần
}
