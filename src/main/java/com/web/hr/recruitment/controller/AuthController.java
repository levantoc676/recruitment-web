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

    // URL: http://localhost:8080/auth/login
    @GetMapping("/login1")
    public String login() {
        return "auth/login";
        // -> Spring Boot sẽ tìm file templates/auth/login.html
    }

    @PostMapping("/login")
    public String login(@RequestParam String email,
        @RequestParam String password,
        Model model) {

        Optional<User> userOpt = userRepository.findByUser(email, password);
        if(userOpt.isPresent()) {
            // Login thành công -> chuyển tới index
            return "redirect:/auth/index";
        } else {
            // Login thất bại -> hiển thị message trên trang login
            model.addAttribute("errorMessage", "Sai tên đăng nhập hoặc mật khẩu");
            return "auth/login";  // Không redirect, trả về template
        }
    }


    // URL: http://localhost:8080/auth/register
    @GetMapping("/register")
    public String register() {
        return "auth/register";
        // -> Spring Boot sẽ tìm file templates/auth/register.html
    }
<<<<<<< HEAD

    // URL: http://localhost:8080/auth/register
    @GetMapping("/index")
    public String index() {
        return "layout/index";
=======
    @GetMapping("/dashboard")
    public String dashboard() {
        return "layout/dashboard";
>>>>>>> loilv_dev1
        // -> Spring Boot sẽ tìm file templates/auth/register.html
    }
}
