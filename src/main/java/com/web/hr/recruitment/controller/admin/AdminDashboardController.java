package com.web.hr.recruitment.controller.admin;

import com.web.hr.recruitment.service.JobService;
import com.web.hr.recruitment.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/admin")
@Controller
public class AdminDashboardController {

  @Autowired
  private UserService userService;
  @Autowired
  private JobService jobService;

  @GetMapping("/dashboard")
  public String dashboard(Model model) {
    long totalUsers = userService.countUsers();
    long totalJobs = jobService.countJobs();
    model.addAttribute("totalUsers", totalUsers);
    model.addAttribute("totalJobs", totalJobs);
    return "admin/dashboard";
  }
}
