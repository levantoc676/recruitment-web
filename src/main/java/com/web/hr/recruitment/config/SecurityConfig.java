package com.web.hr.recruitment.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

  private final CustomUserDetailsService userDetailsService;

  public SecurityConfig(CustomUserDetailsService userDetailsService) {
    this.userDetailsService = userDetailsService;
  }

  // PasswordEncoder bean
  @Bean
  public PasswordEncoder passwordEncoder() {
    //    return new BCryptPasswordEncoder();
    return NoOpPasswordEncoder.getInstance(); // chỉ dùng trong dev
  }


  // AuthenticationManager bean, dùng để Spring Security xử lý login
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
            .defaultSuccessUrl("/index", true)
            .failureUrl("/auth/login?error") // show msg khi sai login
        )
        .logout(logout -> logout
            .logoutUrl("/auth/logout")
            .logoutSuccessUrl("/auth/login")
        );

    return http.build();
  }
}
