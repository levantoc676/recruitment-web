package com.web.hr.recruitment.repository;

import com.web.hr.recruitment.entity.Job;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface JobRepository extends JpaRepository<Job, Long> {

  @Query(value = "SELECT * FROM jobs", nativeQuery = true)
  Optional<Job> findTop10Jobs();
}
