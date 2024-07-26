package com.ducnm.tourapp.repository;

import com.ducnm.tourapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    User findByResetPasswordToken(String resetPasswordToken);
}
