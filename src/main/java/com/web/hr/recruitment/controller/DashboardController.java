package com.web.hr.recruitment.controller;

import com.web.hr.recruitment.entity.Job;
import com.web.hr.recruitment.entity.User;
import com.web.hr.recruitment.repository.JobRepository;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;
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
  @GetMapping("/")
  public String index(HttpSession session, Model model) {
    User user = (User) session.getAttribute("user");
    model.addAttribute("user", user);

    List<Job> allJob = jobRepository.findAll();
    model.addAttribute("jobs", allJob);

    return "index";
  }

  @GetMapping("/login")
  public String login() {
    return "auth/login";
  }

  @GetMapping("/logout")
  public String logout(HttpSession session) {
    // Xóa toàn bộ session
    session.invalidate();

    // Sau đó quay về trang login
    return "redirect:/login";
  }
}
