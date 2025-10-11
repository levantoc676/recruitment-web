package com.web.hr.recruitment.service;

import com.web.hr.recruitment.entity.user.User;
import java.util.List;
import org.springframework.data.domain.Page;

public interface UserService {

  Page<User> getUsers(int page, int size, String sort, String role, String q);

  long countUsers();

  User saveOrUpdateUser(User user);

  User getUserById(Long id);

  List<User> getAllUsers();

  void deleteUser(Long userId);

  boolean lockUser(Long userId);

  boolean unlockUser(Long userId);

  boolean createPasswordResetToken(String email);

  void resetPassword(String token, String newPassword);

  String validatePasswordResetToken(String token);
}
