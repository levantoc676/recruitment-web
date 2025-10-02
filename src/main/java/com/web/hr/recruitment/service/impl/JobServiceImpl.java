package com.web.hr.recruitment.service.impl;

import com.web.hr.recruitment.entity.Job;
import com.web.hr.recruitment.repository.JobRepository;
import com.web.hr.recruitment.service.JobService;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JobServiceImpl implements JobService {

  @Autowired
  private JobRepository jobRepository;

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

  @Override
  public long countJobs() {
    return jobRepository.count();
  }

  @Override
  public Page<Job> getJobs(int page, int size, String sort, String q) {
    Pageable pageable;
    if (sort != null && !sort.isEmpty()) {
      String[] sortParams = sort.split(",");
      Sort.Direction direction = sortParams.length > 1 && sortParams[1].equalsIgnoreCase("desc")
          ? Sort.Direction.DESC : Sort.Direction.ASC;
      pageable = PageRequest.of(page, size, Sort.by(direction, sortParams[0]));
    } else {
      pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "createDate"));
    }

    // tạm thời chỉ trả tất cả jobs, có thể cải tiến search sau
    return jobRepository.findAll(pageable);
  }
}
