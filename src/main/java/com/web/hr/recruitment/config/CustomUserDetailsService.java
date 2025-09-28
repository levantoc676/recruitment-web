package com.web.hr.recruitment.config;

import com.web.hr.recruitment.entity.User;
import com.web.hr.recruitment.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

  private final UserRepository userRepository;

  public CustomUserDetailsService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

//  @Override
//  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//    User user = userRepository.findByEmail(email)
//        .orElseThrow(() -> new UsernameNotFoundException("User not found"));
//
//    // Lấy role từ DB, có thể nhiều role phân tách bằng dấu ","
//    List<GrantedAuthority> authorities = Arrays.stream(user.getRole().split(","))
//        .map(String::trim)
//        .map(r -> new SimpleGrantedAuthority("ROLE_" + r.toUpperCase()))
//        .collect(Collectors.toList());
//
//    return org.springframework.security.core.userdetails.User.builder()
//        .username(user.getUsername())
//        .password(user.getPassword()) // hashed password
//        .authorities(authorities)
//        .build();
//  }

  @Override
  public UserDetails loadUserByUsername(String emailOrUsername) throws UsernameNotFoundException {
    User user = userRepository.findByEmail(emailOrUsername)
        .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    return new CustomUserDetails(user);
  }

}
