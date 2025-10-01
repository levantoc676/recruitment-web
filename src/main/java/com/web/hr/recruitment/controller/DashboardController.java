package com.web.hr.recruitment.controller;

import com.web.hr.recruitment.entity.Job;
import com.web.hr.recruitment.entity.User;
import com.web.hr.recruitment.repository.JobRepository;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class DashboardController {

  @Autowired
  private JobRepository jobRepository;

  // URL: http://localhost:8080/
  @GetMapping("/index")
  public String index(HttpSession session, Model model) {
    User user = (User) session.getAttribute("user");
    model.addAttribute("user", user);
    model.addAttribute("isCreatePage", true);

    // Tất cả job
    List<Job> allJob = jobRepository.findAll();
    model.addAttribute("jobs", allJob);

    // Top 10 job mới nhất
    List<Job> latestJobs = jobRepository.findTop10ByOrderBycreateDateDesc();
    model.addAttribute("latestJobs", latestJobs);

    // Top 10 job nhiều view nhất
    List<Job> mostViewedJobs = jobRepository.findTop10ByOrderByViewsDesc();
    model.addAttribute("mostViewedJobs", mostViewedJobs);

    // Top 5 job sắp hết hạn
    List<Job> expiringSoonJobs = jobRepository.findTop5ByOrderByDeadlineAsc();
    model.addAttribute("expiringSoonJobs", expiringSoonJobs);
    return "index";
  }
}
