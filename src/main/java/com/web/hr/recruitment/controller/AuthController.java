package com.web.hr.recruitment.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auth")
public class AuthController {

    // URL: http://localhost:8080/auth/login
    @GetMapping("/login")
    public String login() {
        return "auth/login";
        // -> Spring Boot sẽ tìm file templates/auth/login.html
    }

    // URL: http://localhost:8080/auth/register
    @GetMapping("/register")
    public String register() {
        return "auth/register";
        // -> Spring Boot sẽ tìm file templates/auth/register.html
    }
    @GetMapping("/index")
    public String index() {
        return "dashboard/index";
        // -> Spring Boot sẽ tìm file templates/dashboard/index.html
    }
}
