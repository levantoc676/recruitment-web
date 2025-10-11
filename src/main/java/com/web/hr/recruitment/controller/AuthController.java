package com.web.hr.recruitment.controller;

import com.web.hr.recruitment.repository.user.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auth")
public class AuthController {

  @Autowired
  private UserRepository userRepository;

  @GetMapping("/login")
  public String login() {
    return "auth/login";
  }

//  @PostMapping("/login-web")
//  public String login(@RequestParam String email,
//      @RequestParam String password,
//      Model model, HttpSession session) {
//    System.out.println("XXXXXXXXXXXXXXXXXXX");
//    Optional<User> userOpt = userRepository.findByUser(email, password);
//    if (userOpt.isPresent()) {
//      // Login thành công -> chuyển tới index
//      session.setAttribute("user", userOpt.get());
//      return "redirect:/index";
//    } else {
//      // Login thất bại -> hiển thị message trên trang login
//      model.addAttribute("errorMessage", "Sai tên đăng nhập hoặc mật khẩu");
//      model.addAttribute("email", email);  // giữ email nhập
//      return "auth/login";  // Không redirect, trả về template
//    }
//  }

  // URL: http://localhost:8080/auth/register
  @GetMapping("/register")
  public String register() {
    return "auth/register";
    // -> Spring Boot sẽ tìm file templates/auth/register.html
  }

  @GetMapping("/logout")
  public String logout(HttpSession session) {
    // Xóa toàn bộ session
    session.invalidate();
    // Sau đó quay về trang login
    return "redirect:/login";
  }

}
