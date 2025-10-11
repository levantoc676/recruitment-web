package com.web.hr.recruitment.controller.admin;

import com.web.hr.recruitment.entity.Job;
import com.web.hr.recruitment.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/admin")
public class AdminJobController {

  @Autowired
  private JobService jobService;

  // Hiển thị danh sách job trên giao diện Thymeleaf
  @GetMapping("/jobs")
  public String listJobs(
      @RequestParam(defaultValue = "0") int page,
      @RequestParam(defaultValue = "10") int size,
      @RequestParam(required = false) String sort,
      @RequestParam(required = false) String q,
      Model model
  ) {
    Page<Job> jobs = jobService.getJobs(page, size, sort, q);

    model.addAttribute("jobs", jobs.getContent());
    model.addAttribute("currentPage", page);
    model.addAttribute("totalPages", jobs.getTotalPages());
    model.addAttribute("q", q);

    return "admin/job-list"; // trỏ đến file templates/admin/job-list.html
  }
}
