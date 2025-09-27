package com.web.hr.recruitment.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class DashboardController {
  // URL: http://localhost:8080/
  @GetMapping("/")
  public String dashboard() {
    return "index";
  }
}
