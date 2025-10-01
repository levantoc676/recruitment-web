package com.web.hr.recruitment.config;

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

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
public class SecurityConfig {

  private final CustomUserDetailsService userDetailsService;

  public SecurityConfig(CustomUserDetailsService userDetailsService) {
    this.userDetailsService = userDetailsService;
  }

  // PasswordEncoder bean
  @Bean
  public PasswordEncoder passwordEncoder() {
    // return new BCryptPasswordEncoder();
    return NoOpPasswordEncoder.getInstance(); // chỉ dùng trong dev
  }

  // AuthenticationManager bean
  @Bean
  public AuthenticationManager authenticationManager() {
    DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
    authProvider.setUserDetailsService(userDetailsService);
    authProvider.setPasswordEncoder(passwordEncoder());
    return new org.springframework.security.authentication.ProviderManager(authProvider);
  }

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
        .csrf(csrf -> csrf.disable())
        .authorizeHttpRequests(auth -> auth
            .requestMatchers("/jobs/create").hasAnyRole("EMPLOYER", "ADMIN")
            .requestMatchers("/jobs/apply").hasAnyRole("CANDIDATE", "ADMIN")
            .requestMatchers("/admin/**").hasRole("ADMIN")
            .requestMatchers("/index", "/auth/login", "/css/**", "/js/**").permitAll()
            .anyRequest().authenticated()
        )
        .formLogin(form -> form
            .loginPage("/auth/login")
            .loginProcessingUrl("/auth/login-web")
            .usernameParameter("email")
            .passwordParameter("password")
            // thay vì defaultSuccessUrl → dùng custom success handler
            .successHandler(customSuccessHandler())
            .failureUrl("/auth/login?error")
        )
        .logout(logout -> logout
            .logoutUrl("/auth/logout")
            .logoutSuccessUrl("/auth/login")
        );

    return http.build();
  }

  // Custom Success Handler để redirect theo role
  @Bean
  public AuthenticationSuccessHandler customSuccessHandler() {
    return new AuthenticationSuccessHandler() {
      @Override
      public void onAuthenticationSuccess(HttpServletRequest request,
          HttpServletResponse response,
          Authentication authentication)
          throws IOException, ServletException {

        String authorities = authentication.getAuthorities().toString(); // [ROLE_ADMIN, ROLE_EMPLOYER,...]

        if (authorities.contains("ADMIN")) {
          response.sendRedirect("/admin/dashboard");
        } else {
          response.sendRedirect("/index");
        }
      }
    };
  }
}
