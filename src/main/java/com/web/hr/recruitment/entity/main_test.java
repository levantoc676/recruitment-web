package com.web.hr.recruitment.entity;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class main_test {

  public static void main(String[] args) {
    // Tạo PasswordEncoder
    PasswordEncoder encoder = new BCryptPasswordEncoder();

    // Mật khẩu gốc
    String rawPassword = "1";

    // Hash mật khẩu
    String hashedPassword = encoder.encode(rawPassword);

    // In ra kết quả
    System.out.println("Mật khẩu gốc: " + rawPassword);
    System.out.println("Mật khẩu đã hash: " + hashedPassword);
  }
}
