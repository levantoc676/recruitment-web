package com.web.hr.recruitment.config;

import com.web.hr.recruitment.entity.User;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class CustomUserDetails implements UserDetails {

  private final User user;
  private final List<GrantedAuthority> authorities;

  public CustomUserDetails(User user) {
    this.user = user;
    // convert role từ DB
    this.authorities = List.of(user.getRole().split(",")).stream()
        .map(String::trim)
        .map(r -> new SimpleGrantedAuthority("ROLE_" + r.toUpperCase()))
        .collect(Collectors.toList());
  }

  public User getUser() {
    return user; // để lấy các thông tin khác: fullName, email, phone, v.v.
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return authorities;
  }

  @Override
  public String getPassword() {
    return user.getPassword();
  }

  @Override
  public String getUsername() {
    return user.getEmail(); // bạn có thể trả về email nếu muốn login bằng email
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }
}
