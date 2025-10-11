package com.web.hr.recruitment.config;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
public class SecurityConfig {

  private final CustomUserDetailsService userDetailsService;

  public SecurityConfig(CustomUserDetailsService userDetailsService) {
    this.userDetailsService = userDetailsService;
  }

  // Bean để mã hoá mật khẩu
  @Bean
  public PasswordEncoder passwordEncoder() {
    // Trong thực tế nên dùng BCryptPasswordEncoder()
    // Ở đây dùng NoOpPasswordEncoder để dev/test (không mã hoá, so sánh plain text)
    return NoOpPasswordEncoder.getInstance();
  }

  // Bean AuthenticationManager quản lý xác thực user
  @Bean
  public AuthenticationManager authenticationManager() {
    DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
    // cấu hình service custom để load user từ DB
    authProvider.setUserDetailsService(userDetailsService);
    // cấu hình password encoder (ở trên)
    authProvider.setPasswordEncoder(passwordEncoder());
    return new org.springframework.security.authentication.ProviderManager(authProvider);
  }

  // Cấu hình chuỗi filter bảo mật
  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
        // Tắt CSRF (chỉ để dev, production nên bật kèm token)
        .csrf(csrf -> csrf.disable())

        // Cấu hình rule phân quyền
        .authorizeHttpRequests(auth -> auth
            // Chỉ Employer mới tạo job
            .requestMatchers("/jobs/create").hasAnyRole("EMPLOYER")
            // Chỉ Candidate mới apply job
            .requestMatchers("/jobs/apply").hasAnyRole("CANDIDATE")

            // Mở quyền cho Forgot Password + Reset Password
            .requestMatchers("/admin/forgot-password", "/admin/reset-password/**").permitAll()

            // Các URL /admin/** khác thì yêu cầu role ADMIN
            .requestMatchers("/admin/**").hasRole("ADMIN")

            // Các page public (ai cũng truy cập được)
            .requestMatchers("/index", "/auth/login", "/css/**", "/js/**").permitAll()

            // Tất cả request khác thì phải login
            .anyRequest().authenticated()
        )

        // Cấu hình login form
        .formLogin(form -> form
            .loginPage("/auth/login")          // Trang login custom
            .loginProcessingUrl("/auth/login-web") // URL form submit login
            .usernameParameter("email")        // field username = email
            .passwordParameter("password")     // field password = password
            .successHandler(customSuccessHandler()) // xử lý khi login thành công
            .failureUrl("/auth/login?error")   // khi login fail redirect về /auth/login?error
        )

        // Cấu hình logout
        .logout(logout -> logout
            .logoutUrl("/auth/logout")         // URL để logout
            .logoutSuccessUrl("/auth/login")   // logout xong về trang login
        );

    return http.build();
  }

  // Custom Success Handler: redirect user theo role sau khi login thành công
  @Bean
  public AuthenticationSuccessHandler customSuccessHandler() {
    return new AuthenticationSuccessHandler() {
      @Override
      public void onAuthenticationSuccess(HttpServletRequest request,
          HttpServletResponse response,
          Authentication authentication)
          throws IOException, ServletException {

        // Lấy danh sách quyền của user hiện tại
        String authorities = authentication.getAuthorities().toString(); // Ví dụ: [ROLE_ADMIN]

        // Nếu có ADMIN thì đưa về dashboard admin
        if (authorities.contains("ADMIN")) {
          response.sendRedirect("/admin/dashboard");
        } else {
          // Ngược lại thì đưa về trang index
          response.sendRedirect("/index");
        }
      }
    };
  }
}
