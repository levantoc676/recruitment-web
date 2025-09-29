package com.web.hr.recruitment.service.impl;

import com.web.hr.recruitment.entity.Job;
import com.web.hr.recruitment.repository.JobRepository;
import com.web.hr.recruitment.service.JobService;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JobServiceImpl implements JobService {

  private final JobRepository jobRepository;

  @Override
  public Job saveJob(Job job) {
    if (job.getCreateDate() == null) {
      job.setCreateDate(LocalDateTime.now());
    }
    job.setUpdatedDate(LocalDateTime.now());
    return jobRepository.save(job);
  }

  @Override
  public List<Job> getAllJobs() {
    return jobRepository.findAll();
  }

  @Override
  public Optional<Job> getJobById(Long id) {
    return jobRepository.findById(id);
  }

  @Override
  public void deleteJob(Long id) {
    jobRepository.deleteById(id);
  }

  @Override
  public Optional<Job> findById(Long jobId) {
    return jobRepository.findById(jobId);
  }
}
