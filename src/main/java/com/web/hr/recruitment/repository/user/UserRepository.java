package com.web.hr.recruitment.repository.user;

import com.web.hr.recruitment.entity.user.User;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Long> {

  @Query(value = "SELECT * FROM users WHERE email = :email AND password = :password", nativeQuery = true)
  Optional<User> findByUser(@Param("email") String email, @Param("password") String password);

  Optional<User> findByUserId(Long userId);

  Optional<User> findByEmail(String email);

  Optional<User> findByUsername(String username);

  Page<User> findAll(Specification<User> spec, Pageable pageable);
}
