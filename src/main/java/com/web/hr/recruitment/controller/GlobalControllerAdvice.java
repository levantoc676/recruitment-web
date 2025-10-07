package com.web.hr.recruitment.controller;

import com.web.hr.recruitment.config.CustomUserDetails;

import com.web.hr.recruitment.entity.user.User;
import com.web.hr.recruitment.repository.user.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class GlobalControllerAdvice {

  @Autowired
  private UserRepository userRepository;

  @ModelAttribute("currentUser")
  public User populateCurrentUser(HttpSession session, Authentication authentication) {

    // 1. Nếu dùng session
    User user = (User) session.getAttribute("user");
    if (user != null) {
      return user;
    }

    // 2. Nếu dùng Spring Security
    if (authentication != null && authentication.isAuthenticated()) {
      Object principal = authentication.getPrincipal();

      // Trường hợp custom UserDetails
      if (principal instanceof CustomUserDetails) {
        return ((CustomUserDetails) principal).getUser();
      }

      // Trường hợp dùng Spring Security UserDetails mặc định
      if (principal instanceof org.springframework.security.core.userdetails.User) {
        String username = ((org.springframework.security.core.userdetails.User) principal).getUsername();
        return userRepository.findByUsername(username).orElse(null);
      }
    }

    return null; // user chưa login
  }
}
