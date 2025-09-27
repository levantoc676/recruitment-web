package com.web.hr.recruitment.controller;

import com.web.hr.recruitment.entity.User;
import com.web.hr.recruitment.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
@RequestMapping("/auth")
public class AuthController {

  @Autowired
  private UserRepository userRepository;

  @PostMapping("/login")
  public String login(@RequestParam String email,
      @RequestParam String password,
      Model model) {

    Optional<User> userOpt = userRepository.findByUser(email, password);
    if (userOpt.isPresent()) {
      // Login thành công -> chuyển tới index
      return "redirect:/";
    } else {
      // Login thất bại -> hiển thị message trên trang login
      model.addAttribute("errorMessage", "Sai tên đăng nhập hoặc mật khẩu");
      model.addAttribute("email", email);  // giữ email nhập
      return "auth/login";  // Không redirect, trả về template
    }
  }

  // URL: http://localhost:8080/auth/register
  @GetMapping("/register")
  public String register() {
    return "auth/register";
    // -> Spring Boot sẽ tìm file templates/auth/register.html
  }

}
