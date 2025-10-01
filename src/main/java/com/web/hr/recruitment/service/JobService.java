package com.web.hr.recruitment.service;

import com.web.hr.recruitment.entity.Job;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public interface JobService {

  Job saveJob(Job job);

  List<Job> getAllJobs();

  Optional<Job> getJobById(Long id);

  void deleteJob(Long id);

  Optional<Job> findById(Long id);

  long countJobs();

  Page<Job> getJobs(int page, int size, String sort, String q);
}
