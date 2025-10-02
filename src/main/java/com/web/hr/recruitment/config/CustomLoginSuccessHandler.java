package com.web.hr.recruitment.config;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

public class CustomLoginSuccessHandler implements AuthenticationSuccessHandler {

  @Override
  public void onAuthenticationSuccess(HttpServletRequest request,
      HttpServletResponse response,
      Authentication authentication) throws IOException, ServletException {

    // Kiểm tra role trong danh sách quyền của user
    for (GrantedAuthority authority : authentication.getAuthorities()) {
      if ("ROLE_ADMIN".equals(authority.getAuthority())) {
        response.sendRedirect("/admin/dashboard"); // Admin → Dashboard
        return;
      }
    }

    // Nếu không phải admin → về trang chủ
    response.sendRedirect("/index");
  }
}
