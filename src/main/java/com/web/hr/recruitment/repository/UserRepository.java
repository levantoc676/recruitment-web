package com.web.hr.recruitment.repository;

import com.web.hr.recruitment.entity.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Long> {

  @Query(value = "SELECT * FROM users WHERE email = :email AND password = :password", nativeQuery = true)
  Optional<User> findByUser(@Param("email") String email, @Param("password") String password);

  Optional<User> findByEmail(String email);
}
