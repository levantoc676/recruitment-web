package com.web.hr.recruitment.service.impl;

import com.web.hr.recruitment.entity.User;
import com.web.hr.recruitment.repository.UserRepository;
import com.web.hr.recruitment.service.UserService;
import jakarta.persistence.criteria.Predicate;
import jakarta.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;
  @Autowired
  private PasswordEncoder passwordEncoder;

  @Override
  public Page<User> getUsers(int page, int size, String sort, String role, String q) {
    Sort sortObj = Sort.unsorted();
    if (sort != null && !sort.isBlank()) {
      String[] parts = sort.split(",");
      if (parts.length == 2) {
        sortObj = Sort.by(Sort.Direction.fromString(parts[1]), parts[0]);
      } else {
        sortObj = Sort.by(parts[0]).ascending();
      }
    }

    Pageable pageable = PageRequest.of(page, size, sortObj);

    Specification<User> spec = (root, query, cb) -> {
      Predicate predicate = cb.conjunction();

      if (role != null && !role.isBlank()) {
        predicate = cb.and(predicate, cb.equal(root.get("role"), role));
      }

      if (q != null && !q.isBlank()) {
        String likeQ = "%" + q.toLowerCase() + "%";
        predicate = cb.and(predicate,
            cb.or(
                cb.like(cb.lower(root.get("username")), likeQ),
                cb.like(cb.lower(root.get("email")), likeQ),
                cb.like(cb.lower(root.get("fullName")), likeQ)
            ));
      }

      return predicate;
    };

    return userRepository.findAll(spec, pageable);
  }

  @Override
  public long countUsers() {
    return userRepository.count();
  }

  @Override
  public User saveOrUpdateUser(User user) {
    if (user.getUserId() != null) {
      // Edit user
      User existing = userRepository.findById(user.getUserId()).orElse(null);
      if (existing != null) {
        existing.setUsername(user.getUsername());
        existing.setEmail(user.getEmail());
        existing.setFullName(user.getFullName());
        existing.setPhone(user.getPhone());
        existing.setRole(user.getRole());
        existing.setUpdatedDate(LocalDateTime.now());

        // Nếu password để trống thì giữ nguyên
        if (user.getPassword() != null && !user.getPassword().isEmpty()) {
          existing.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        return userRepository.save(existing);
      }
    } else {
      // Add new user
      user.setPassword(passwordEncoder.encode(user.getPassword()));
      user.setCreateDate(LocalDateTime.now());
      user.setUpdatedDate(LocalDateTime.now());
      user.setDelFlg(false); // mặc định active
      return userRepository.save(user);
    }
    return null;
  }

  @Override
  public User getUserById(Long id) {
    return userRepository.findById(id).orElse(null);
  }

  @Override
  public List<User> getAllUsers() {
    return userRepository.findAll();
  }

  @Override
  public void deleteUser(Long userId) {
    User user = userRepository.findById(userId)
        .orElseThrow(() -> new RuntimeException("User not found with ID: " + userId));

    // Soft delete
    user.setDelFlg(false);
    userRepository.save(user);
  }

  @Override
  @Transactional
  public boolean lockUser(Long userId) {
    return userRepository.findById(userId)
        .map(user -> {
          user.setLocked(true);
          userRepository.save(user);
          return true;
        })
        .orElse(false);
  }

  @Override
  @Transactional
  public boolean unlockUser(Long userId) {
    return userRepository.findById(userId)
        .map(user -> {
          user.setLocked(false);
          userRepository.save(user);
          return true;
        })
        .orElse(false);
  }
}
